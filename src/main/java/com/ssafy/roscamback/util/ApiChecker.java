package com.ssafy.roscamback.util;

import com.ssafy.roscamback.request.GoogleCheckRequest;
import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.request.ThreatInfo;
import com.ssafy.roscamback.response.CheckResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiChecker {
    @Value("${google.safeBrowsingApiKey}")
    private String googleApiKey;

    //요청 URL
    private final String apiCallUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=";

    public CheckResponse checkUrl(List<ThreatEntry> urls){
        RestTemplate restTemplate = new RestTemplate();
        GoogleCheckRequest googleCheckRequest = GoogleCheckRequest.builder()
            .threatInfo(ThreatInfo.builder()
                .threatEntries(urls)
                .build())
            .build();

        return  restTemplate.postForEntity(apiCallUrl+googleApiKey, googleCheckRequest, CheckResponse.class).getBody();

    }
}
