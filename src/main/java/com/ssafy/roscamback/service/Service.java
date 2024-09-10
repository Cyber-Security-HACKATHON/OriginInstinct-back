package com.ssafy.roscamback.service;

import com.ssafy.roscamback.dto.request.AnalyzeRequest;
import com.ssafy.roscamback.dto.response.AnalyzeResponse;
import java.util.concurrent.CompletableFuture;

public interface Service {

//    CheckResponse checkUrlSafety(List<ThreatEntry> urls);
//    boolean isUrlSafe(ThreatEntry url);
    CompletableFuture<AnalyzeResponse> analyze(AnalyzeRequest request);

}
