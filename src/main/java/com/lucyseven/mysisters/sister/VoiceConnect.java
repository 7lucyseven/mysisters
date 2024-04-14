package com.lucyseven.mysisters.sister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class VoiceConnect {
    private final String apiUrl = "http://127.0.0.1:50021/audio_query";
    private final RestTemplate restTemplate;

    @Autowired
    public VoiceConnect(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> postDataToApi(String apiUrl, Object request) {

        // リクエストボディを作成
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String text = "こんにちは";
        String speaker = "14";


        // パラメーターを設定
        String requestBody = "{\"text\": \"" + text + "\", \"speaker\": \"" + speaker + "\"}";

        return restTemplate.postForEntity(apiUrl, new HttpEntity<>(requestBody, headers), String.class);
    }
}
