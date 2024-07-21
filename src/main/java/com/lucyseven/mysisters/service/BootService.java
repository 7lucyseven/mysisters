package com.lucyseven.mysisters.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lucyseven.mysisters.dto.ChatGptResponseDto;
import com.lucyseven.mysisters.dto.OneComeDto;
import com.lucyseven.mysisters.sister.ChatGptConnect;
import com.lucyseven.mysisters.sister.VoiceConnect;
import com.lucyseven.mysisters.util.ConverterUtil;
import com.lucyseven.mysisters.util.WavePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

@Service
public class BootService {
    RestTemplate restTemplate;
    VoiceConnect voiceConnect;
    ChatGptConnect chatGptConnect;

    @Autowired
    BootService(RestTemplate restTemplate, VoiceConnect voiceConnect, ChatGptConnect chatGptConnect) {
        this.restTemplate = restTemplate;
        this.voiceConnect = voiceConnect;
        this.chatGptConnect = chatGptConnect;
    }

    public void test() {

        //妹ちゃん起動
        ChatGptResponseDto chatGptResponseDto = chatGptConnect.getChatGptResponse("test");
        System.out.println(chatGptResponseDto.getObject());

        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        String urlWithParams = "http://localhost:11180/api/comments";
        //HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //ResponseEntity<List> responseEntity = restTemplate.getForEntity(urlWithParams, List.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWithParams, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        //HashMap map = null;
        //List list =  Arrays.asList(responseEntity.getBody().split(","));

        //System.out.println(list.get(0).get("comment"));
        //System.out.println(list.get(0));
        //System.out.println(list);

        //System.out.println(responseEntity.getBody());
        //System.out.println(list.get(0));
        //JSONObject j = (JSONObject) o;
        System.out.println(responseEntity.getHeaders().getContentType());
        //System.out.println(responseEntity.getBody().get(0));
        String str = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, OneComeDto.class);
        String text = "こんにちは";
        try {
            List<OneComeDto> objects = mapper.readValue(str, listType);
            text = objects.get(0).getData().getComment();
//            for(OneComeDto oneComeDto : objects){
//
//
//            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        try{
//            File file = new File("/tmp/test.txt");
//            FileWriter filewriter = new FileWriter(file);
//            filewriter.write(str);
//            filewriter.close();
//        }catch(IOException e){
//            System.out.println(e);
//        }

        //ConverterUtil converterUtil = new ConverterUtil();
        //converterUtil.ConvertToOneComeList(obj);


        //ViceVox

        byte[] bytes = voiceConnect.postDataToApi(text);

        WavePlayer wavePlayer = new WavePlayer();
        wavePlayer.run(bytes);


    }
}
