package com.lucyseven.mysisters.dto;

import lombok.Data;

@Data
public class OneComeDto {
    private String id;
    private String service;
    private String name;
    private String url;
    private OneComeColorDto color;
    private OneComeDataDto data;
    private OneComeMetaDto meta;
}
