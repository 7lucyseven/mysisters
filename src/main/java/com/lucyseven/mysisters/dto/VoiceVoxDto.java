package com.lucyseven.mysisters.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VoiceVoxDto {
    private String text;
    private int speaker;

}
