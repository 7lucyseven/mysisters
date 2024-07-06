package com.lucyseven.mysisters.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties("openapi.api")
public class OpenApiProperties {
    private String url;
    private String key;
}
