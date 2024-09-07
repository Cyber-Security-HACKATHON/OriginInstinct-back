package com.ssafy.roscamback.controller;

import com.ssafy.roscamback.request.AnalyzeRequest;
import com.ssafy.roscamback.request.ThreatEntry;
import com.ssafy.roscamback.response.AnalyzeResponse;
import com.ssafy.roscamback.response.CheckResponse;
import com.ssafy.roscamback.service.Service;
import com.ssafy.roscamback.util.ApiChecker;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class Controller {

    private final Service service;

//    @PostMapping("/test")
//    public CheckResponse testCheckUrl(@RequestBody ThreatEntry url) {
//
//        return apiChecker.checkUrlTest(Collections.singletonList(url));
//    }

    @PostMapping("/analyze")
    public CompletableFuture<AnalyzeResponse> analyzeChat(@RequestBody AnalyzeRequest request) {

        return service.analyze(request);
    }

    // 일치하는 위협 정보의 값 리턴. 없다면 null 반환
//    @PostMapping("/sigleUrlCheck")
//    public CheckResponse checkSingleUrlSafety(@RequestBody ThreatEntry url){
//
//        return service.checkUrlSafety(Collections.singletonList(url));
//    }
//
//    // 최대 500개까지 포함가능
//    @PostMapping("/multipleUrlCheck")
//    public CheckResponse checkMultipleUrlsSafety(@RequestBody List<ThreatEntry> urls){
//        return service.checkUrlSafety(urls);
//    }

    // 위협 정보를 제외한 단순 boolean값의 유무
//    @PostMapping("/simpleUrlSafety")
//    public ResponseEntity<Boolean> checkSimpleUrlSafety(@RequestBody ThreatEntry url){
//        return new ResponseEntity<>(service.isUrlSafe(url), HttpStatus.ACCEPTED);
//    }

}
