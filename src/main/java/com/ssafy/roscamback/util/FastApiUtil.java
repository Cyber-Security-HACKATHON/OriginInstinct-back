package com.ssafy.roscamback.util;

import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.ChatAnalysisResponse;
import com.ssafy.roscamback.response.CheckResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;

@Component
public class FastApiUtil {

    private final String apiCallUrl = "";   //FastApi 엔드포인트 연결

    /**
     * CompletableFuture 비동기 메서드 작성해주시면 됩니다.
     * **/


    public CompletableFuture<ChatAnalysisResponse> analyzeChat() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.supplyAsync(() -> {
            return new ChatAnalysisResponse();
        });
    }
}
