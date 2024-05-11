package com.lucyseven.mysisters.dto;

import lombok.Data;

@Data
public class UsageDto {
    public int prompt_tokens;
    public int completion_tokens;
    public int total_tokens;

}

