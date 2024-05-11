package com.lucyseven.mysisters.sister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Component
public class VoiceConnect {
    private final String apiUrl_audio_query = "http://127.0.0.1:50021/audio_query";
    private final String apiUrl_synthesis = "http://127.0.0.1:50021/synthesis";
    private final RestTemplate restTemplate;

    @Autowired
    public VoiceConnect(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] postDataToApi(String text) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String speaker = "14";

        // パラメーターを設定
        String urlWithParams = apiUrl_audio_query + "?text=" + text + "&speaker=" + speaker;
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlWithParams, requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(responseEntity.getBody(), HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String urlWithParams2 = apiUrl_synthesis + "?speaker=" + speaker;
        ResponseEntity<byte[]> responseEntity2 = restTemplate.postForEntity(urlWithParams2, map, byte[].class);

        return responseEntity2.getBody();
    }
}
