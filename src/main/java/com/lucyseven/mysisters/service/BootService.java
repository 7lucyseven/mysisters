package com.lucyseven.mysisters.service;

import com.lucyseven.mysisters.dto.ChatGptResponseDto;
import com.lucyseven.mysisters.sister.ChatGptConnect;
import com.lucyseven.mysisters.sister.VoiceConnect;
import com.lucyseven.mysisters.util.WavePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootService {
    VoiceConnect voiceConnect;
    ChatGptConnect chatGptConnect;

    @Autowired
    BootService(VoiceConnect voiceConnect, ChatGptConnect chatGptConnect) {
        this.voiceConnect = voiceConnect;
        this.chatGptConnect = chatGptConnect;
    }

    public void test() {

        //妹ちゃん起動
        ChatGptResponseDto chatGptResponseDto = chatGptConnect.getChatGptResponse("test");
        System.out.println(chatGptResponseDto.getObject());

        //ViceVox
        String text = "こんにちは";
        byte[] bytes = voiceConnect.postDataToApi(text);

        WavePlayer wavePlayer = new WavePlayer();
        wavePlayer.run(bytes);


    }


}
