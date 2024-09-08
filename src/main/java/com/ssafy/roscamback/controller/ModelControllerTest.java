package com.ssafy.roscamback.controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8000/predict";

    public String getExplanation(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestJson = "{\"message\":\"" + message + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


        return response.getBody();
    }
}