package com.lucyseven.mysisters.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequestDto {
    private String model;
    private List<MessageDto> messages;
    private Integer max_tokens;
}


