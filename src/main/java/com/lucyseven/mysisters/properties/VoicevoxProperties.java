package com.lucyseven.mysisters.properties;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties("voicevox")
public class VoicevoxProperties {
    private String apiUrl;
    private Map<String, String> endpoint;
    private String speaker;

}
