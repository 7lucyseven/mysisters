package com.lucyseven.mysisters.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptResponseDto {
    public String id;
    public String object;
    public int created;
    public List<ChoiceDto> choices;
    public UsageDto usage;

}
