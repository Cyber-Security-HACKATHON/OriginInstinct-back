package com.ssafy.roscamback.service;

import com.ssafy.roscamback.dto.response.ChatAnalysisResponse;
import com.ssafy.roscamback.module.ChatSaveEvent;
import com.ssafy.roscamback.dto.request.AnalyzeRequest;
import com.ssafy.roscamback.dto.request.ThreatEntry;
import com.ssafy.roscamback.dto.response.AnalyzeResponse;
import com.ssafy.roscamback.dto.response.CheckResponse;
import com.ssafy.roscamback.util.ApiChecker;
import com.ssafy.roscamback.util.FastApiUtil;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceImpl implements com.ssafy.roscamback.service.Service {

    private ApiChecker apiChecker;

    private FastApiUtil fastApiUtil;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CompletableFuture<AnalyzeResponse> analyze(AnalyzeRequest request) {
        // Step 1. URL 정규표현식 추출
        ThreatEntry url = ThreatEntry.builder()
            .url(parsingUrlFromChatList(request.getData()))
            .build();

        ChatSaveEvent event = new ChatSaveEvent(request.getData());
        eventPublisher.publishEvent(event);

        // Step 2. fastAPI 분석 비동기 요청
        CompletableFuture<ChatAnalysisResponse> chatAnalysisFuture = fastApiUtil.analyzeChat(request.getData());

        // Step 3. url 파싱이 된 경우 구글 세이프 브라우징 API 비동기 요청
        if(!url.getUrl().isEmpty()) {
            CompletableFuture<CheckResponse> urlInspectionFuture = apiChecker.checkUrl(url);

            CompletableFuture.allOf(chatAnalysisFuture, urlInspectionFuture).join();

            // URL 검사와 채팅 분석의 결과를 결합하여 최종 응답 생성
            return urlInspectionFuture.thenCombine(chatAnalysisFuture, (urlResult, chatResult) -> {
                AnalyzeResponse finalResponse = new AnalyzeResponse();

                if(urlResult.getThreatMatches() != null) {
                    finalResponse.setBadUrl(true);
                }

                return finalResponse;
            });
        }

        else {
            // URL 파싱이 안된 경우 채팅 분석 결과만 응답
            return chatAnalysisFuture.thenApply(chatResult -> {
                AnalyzeResponse response = new AnalyzeResponse();
                response.setChatResponse(chatResult);
                return response;
            });
        }



    }

    /**
     * URL 정규표현식 추출 메서드
     * **/
    private static String parsingUrlFromChatList(String origin) {

        String urlPattern = "((http[s]?|ftp):\\/\\/)?(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=가-힣]{1,256}[:|\\.][a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+,.~#?&\\/=가-힣]*)";
        Pattern pt = Pattern.compile(urlPattern);
        Matcher mc = pt.matcher(origin);

        StringBuffer sb = new StringBuffer();

        while (mc.find()) {
            return mc.group();
        }

        return "";
    }

}
