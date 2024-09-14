package com.ssafy.roscamback.controller;

import com.ssafy.roscamback.dto.request.AnalyzeRequest;
import com.ssafy.roscamback.dto.response.AnalyzeResponse;
import com.ssafy.roscamback.service.Service;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class Controller {

    private final Service service;

    private final ApplicationEventPublisher events;
//    @PostMapping("/test")
//    public CheckResponse testCheckUrl(@RequestBody ThreatEntry url) {
//
//        return apiChecker.checkUrlTest(Collections.singletonList(url));
//    }

    @GetMapping("/test")
    public String test() {
        return "HHI"    ;
    }
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
