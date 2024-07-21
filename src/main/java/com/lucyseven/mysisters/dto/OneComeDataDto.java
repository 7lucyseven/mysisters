package com.lucyseven.mysisters.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OneComeDataDto {
    private String id;
    private String liveId;
    private String userId;
    private String name;
    private String profileImage;
    private List<OneComeBadgeDto> badges;
    private String isOwner;
    private String isModerator;
    private String isMember;
    private String autoModerated;
    private String hasGift;
    private String comment;
    private String timestamp;
    private String displayName;
    private String originalProfileImage;
    private Map meta;
    private String speechText;
    private String isFirstTime;
}
