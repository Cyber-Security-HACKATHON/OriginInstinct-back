package com.ssafy.roscamback.util;

import com.ssafy.roscamback.dto.request.MessageRequest;
import com.ssafy.roscamback.dto.response.ChatAnalysisResponse;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FastApiUtil {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiCallUrl = "http://localhost:8000/predict"; //FastApi 엔드포인트 연결
    /**
     * CompletableFuture 비동기 메서드 작성해주시면 됩니다.
     * **/

    public CompletableFuture<ChatAnalysisResponse> analyzeChat(String message) {

        MessageRequest request = new MessageRequest("{\"message\":\"" + message + "\"}");

        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.postForEntity(apiCallUrl, request , ChatAnalysisResponse.class).getBody();
        });
    }
}
