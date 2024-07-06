package com.lucyseven.mysisters.sister;

import com.lucyseven.mysisters.dto.ChatGptRequestDto;
import com.lucyseven.mysisters.dto.ChatGptResponseDto;
import com.lucyseven.mysisters.dto.MessageDto;
import com.lucyseven.mysisters.properties.OpenApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ChatGptConnect {

    private final String apiKey;
    private final String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    ChatGptConnect(RestTemplate restTemplate, OpenApiProperties openApiProperties){
        this.restTemplate = restTemplate;
        this.apiUrl = openApiProperties.getUrl();
        this.apiKey = openApiProperties.getKey();
    }

    public ChatGptResponseDto getChatGptResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ChatGptRequestDto chatGptRequestDto = new ChatGptRequestDto();
        chatGptRequestDto.setModel("gpt-3.5-turbo"); //モデル名
        chatGptRequestDto.setMessages(List.of(new MessageDto("system", prompt), new MessageDto("user", prompt))); //メッセージ指定
        chatGptRequestDto.setMax_tokens(20); //一回で返す最大Token数

        HttpEntity<ChatGptRequestDto> request = new HttpEntity<>(chatGptRequestDto, headers);

        return restTemplate.postForObject(apiUrl, request, ChatGptResponseDto.class);
    }
}
