package com.lucyseven.mysisters.util;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class WavePlayer {
    public static void run(byte[] bytes) {
        //System.out.println(responseEntity.getBody());
        try {

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 1. Waveファイルの読み込み
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bais);

            // 2. AudioInputStreamの生成
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            // 3. 音声ラインの取得
            line.open(format);
            line.start();

            // 4. 音声の再生
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            // 再生が終了したら閉じる
            line.drain();
            line.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

