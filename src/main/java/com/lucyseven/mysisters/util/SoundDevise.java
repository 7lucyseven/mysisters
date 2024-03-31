package com.lucyseven.mysisters.util;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundDevise {


    public List<String> getList(){
        List<String> deviseList = new ArrayList<>();
        // サウンドシステムの情報を取得
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        // 取得した各ミキサー情報をListに格納
        for (Mixer.Info info : mixerInfos) {
            deviseList.add(info.getName());
//            System.out.println("Mixer: " + info.getName());
//            System.out.println("Description: " + info.getDescription());
//            System.out.println("Vendor: " + info.getVendor());
//            System.out.println("Version: " + info.getVersion());
        }
        return deviseList;

    }

    public void test() {
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        // 例えば、デフォルトの再生デバイスを取得する場合
        //Mixer mixer = AudioSystem.getMixer(null);

        // サウンドシステムの情報を取得
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        // 取得した各ミキサー情報を出力
        for (Mixer.Info info : mixerInfos) {
            System.out.println("Mixer: " + info.getName());
            System.out.println("Description: " + info.getDescription());
            System.out.println("Vendor: " + info.getVendor());
            System.out.println("Version: " + info.getVersion());
            System.out.println();
        }

        try {

            Mixer.Info targetMixerInfo = findTargetMixer("MacBook Proのスピーカー");

            // オーディオファイルの読み込み
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/lucy/Downloads/cat1.wav"));

            // 再生フォーマットの取得
            AudioFormat format = audioInputStream.getFormat();

            // データライン情報の作成
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            // データラインの取得
            Mixer mixer = AudioSystem.getMixer(targetMixerInfo);
            SourceDataLine line = (SourceDataLine) mixer.getLine(info);

            // データラインを開く
            line.open(format);

            // 再生開始
            line.start();

            // 音声データの読み込みおよび再生
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            // 再生終了
            line.drain();
            line.stop();
            line.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 指定された名前の再生デバイスを見つけるヘルパーメソッド
    private static Mixer.Info findTargetMixer(String targetDeviceName) {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mixerInfos) {
            if (info.getName().equals(targetDeviceName)) {
                return info;
            }
        }
        return null;
    }

}
