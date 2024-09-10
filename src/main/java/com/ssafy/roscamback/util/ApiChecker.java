package com.ssafy.roscamback.util;

import com.ssafy.roscamback.dto.request.GoogleCheckRequest;
import com.ssafy.roscamback.dto.request.ThreatEntry;
import com.ssafy.roscamback.dto.request.ThreatInfo;
import com.ssafy.roscamback.dto.response.CheckResponse;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiChecker {
    @Value("${google.safeBrowsingApiKey}")
    private String googleApiKey;

    //요청 URL
    private final String apiCallUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=";
    public CompletableFuture<CheckResponse> checkUrl(ThreatEntry url){
        RestTemplate restTemplate = new RestTemplate();
        GoogleCheckRequest googleCheckRequest = GoogleCheckRequest.builder()
            .threatInfo(ThreatInfo.builder()
                .threatEntries(url)
                .build())
            .build();

        return  CompletableFuture.supplyAsync(() -> {
            return restTemplate.postForEntity(apiCallUrl+googleApiKey, googleCheckRequest, CheckResponse.class).getBody();
        });

    }

}
