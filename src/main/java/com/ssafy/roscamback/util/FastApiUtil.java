package com.ssafy.roscamback.util;

import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.ChatAnalysisResponse;
import com.ssafy.roscamback.response.CheckResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FastApiUtil {

    private final String apiCallUrl = "";   //FastApi 엔드포인트 연결

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8000/predict";
    /**
     * CompletableFuture 비동기 메서드 작성해주시면 됩니다.
     * **/

    public CompletableFuture<String> analyzeChat() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String message = "돈을 빌려줄 수 있나요? 제가 사업을 시작하려고 해요.";
        String requestJson = "{\"message\":\"" + message + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

//        String tmpMsg = "";
//        String requestJson = "{\"message\":\"" + tmpMsg + "\"}";
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.postForEntity(url,entity , String.class).getBody();
        });
    }
}
