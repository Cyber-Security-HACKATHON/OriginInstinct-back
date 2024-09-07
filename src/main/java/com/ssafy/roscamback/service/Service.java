package com.ssafy.roscamback.service;

import com.ssafy.roscamback.request.AnalyzeRequest;
import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.AnalyzeResponse;
import com.ssafy.roscamback.response.CheckResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Service {

//    CheckResponse checkUrlSafety(List<ThreatEntry> urls);
//    boolean isUrlSafe(ThreatEntry url);
    CompletableFuture<AnalyzeResponse> analyze(AnalyzeRequest request);

}
