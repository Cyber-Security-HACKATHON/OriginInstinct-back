package com.ssafy.roscamback.service;

import com.ssafy.roscamback.request.AnalyzeRequest;
import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.AnalyzeResponse;
import com.ssafy.roscamback.response.ChatAnalysisResponse;
import com.ssafy.roscamback.response.CheckResponse;
import com.ssafy.roscamback.util.ApiChecker;
import com.ssafy.roscamback.util.FastApiUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceImpl implements com.ssafy.roscamback.service.Service {

    private ApiChecker apiChecker;

    private FastApiUtil fastApiUtil;

//    @Override
//    public CheckResponse checkUrlSafety(List<ThreatEntry> urls) {
//        return apiChecker.checkUrl(urls);
//    }

//    @Override
//    public boolean isUrlSafe(ThreatEntry url) {
//        return apiChecker.checkUrl(Collections.singletonList(url)).getThreatMatches() == null;
//    }

    @Override
    public CompletableFuture<AnalyzeResponse> analyze(AnalyzeRequest request) {
        // Step 1. URL 정규표현식 추출
        List<String> urlList = parsingUrlFromChatList(request.getChatList());
        List<ThreatEntry> parsingList = new ArrayList<>();

        for(String url : urlList) {
            log.info(url);
            parsingList.add(ThreatEntry.builder()
                .url(url)
                .build());
        }

        CompletableFuture<CheckResponse> urlInspectionFuture = apiChecker.checkUrl(parsingList);
        CompletableFuture<ChatAnalysisResponse> chatAnalysisFuture = fastApiUtil.analyzeChat();

            return urlInspectionFuture.thenCombine(chatAnalysisFuture, (urlResult, chatResult) -> {
                AnalyzeResponse finalResponse = new AnalyzeResponse();

                if(urlResult.getThreatMatches() != null) {
                    finalResponse.setBadUrl(true);
                }

                return finalResponse;
            });
    }

    /**
     * URL 정규표현식 추출 메서드
     * **/
    private static List<String> parsingUrlFromChatList(List<String> chatList) {
        List<String> urlList = new ArrayList<>();

        for(String origin : chatList) {
            String urlPattern = "((http[s]?|ftp):\\/\\/)?(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=가-힣]{1,256}[:|\\.][a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+,.~#?&\\/=가-힣]*)";
            Pattern pt = Pattern.compile(urlPattern);
            Matcher mc = pt.matcher(origin);

            StringBuffer sb = new StringBuffer();
            while (mc.find()) {
                urlList.add(mc.group());
                mc.appendReplacement(sb, Matcher.quoteReplacement(mc.group()));
            }
            mc.appendTail(sb);

        }
        return urlList;
    }

}
