package com.lucyseven.mysisters.sister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucyseven.mysisters.properties.VoicevoxProperties;
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
    private final RestTemplate restTemplate;
    private final VoicevoxProperties voicevoxProperties;
    private final String apiUrl;
    private final String endpointAudioQuery;
    private final String endpointSynthesis;
    private final String speaker;


    @Autowired
    public VoiceConnect(RestTemplate restTemplate, VoicevoxProperties voicevoxProperties) {
        this.restTemplate = restTemplate;
        this.voicevoxProperties = voicevoxProperties;
        this.apiUrl = voicevoxProperties.getApiUrl();
        this.endpointAudioQuery = voicevoxProperties.getEndpoint().get("audioQuery");
        this.endpointSynthesis = voicevoxProperties.getEndpoint().get("synthesis");
        this.speaker = voicevoxProperties.getSpeaker();
    }

    public byte[] postDataToApi(String text) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // パラメーターを設定
        String urlWithParams = apiUrl + endpointAudioQuery + "?text=" + text + "&speaker=" + speaker;
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlWithParams, requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(responseEntity.getBody(), HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String urlWithParams2 = apiUrl + endpointSynthesis + "?speaker=" + speaker;
        ResponseEntity<byte[]> responseEntity2 = restTemplate.postForEntity(urlWithParams2, map, byte[].class);

        return responseEntity2.getBody();
    }
}
