package com.lucyseven.mysisters.service;

import com.lucyseven.mysisters.dto.VoiceVoxDto;
import com.lucyseven.mysisters.sister.VoiceConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootService {
    VoiceConnect voiceConnect;
    VoiceVoxDto voiceVoxDto;

    @Autowired
    BootService(VoiceConnect voiceConnect, VoiceVoxDto voiceVoxDto){
        this.voiceConnect = voiceConnect;
        this.voiceVoxDto = voiceVoxDto;

    }

    public void test() {

        //妹ちゃん起動

        //Vice
        String apiUrl = "http://127.0.0.1:50021/audio_query";
        //VoiceVoxDto voiceVoxDto = new VoiceVoxDto();
        voiceVoxDto.setText("こんにちは");
        voiceVoxDto.setSpeaker(14);

        voiceConnect.postDataToApi(apiUrl, voiceVoxDto);
    }


}
