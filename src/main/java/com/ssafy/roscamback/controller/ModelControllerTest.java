package com.ssafy.roscamback.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ModelControllerTest {

//
//    @PostMapping("/predict")
//    public String getExplanation(String message) {
////        HttpHeaders headers = new HttpHeaders();
////        headers.set("Content-Type", "application/json");
////
////        String requestJson = "{\"message\":\"" + message + "\"}";
////        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
////
////        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//        return response.getBody();
//    }
}