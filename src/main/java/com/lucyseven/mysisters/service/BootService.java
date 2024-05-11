package com.lucyseven.mysisters.service;

import com.lucyseven.mysisters.sister.VoiceConnect;
import com.lucyseven.mysisters.util.WavePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootService {
    VoiceConnect voiceConnect;

    @Autowired
    BootService(VoiceConnect voiceConnect){
        this.voiceConnect = voiceConnect;
    }

    public void test() {

        //妹ちゃん起動

        //ViceVox
        String text = "こんにちは";
        byte[] bytes = voiceConnect.postDataToApi(text);

        WavePlayer wavePlayer = new WavePlayer();
        wavePlayer.run(bytes);


    }


}
