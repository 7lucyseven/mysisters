package com.lucyseven.mysisters.dto;

import lombok.Data;

@Data
public class ChoiceDto {
    public int index;
    public MessageDto message;
    public String finish_reason;

}

