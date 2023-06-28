package com.xiaoxiong.bumblebee.chat.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author 六月
 * @Date 2023/6/28 11:35
 * @Version 1.0
 */
@Data
public class ChatRecord {

    private Integer id;
    private String fromCode;
    private String fromName;
    private String mappingCode;
    private String toCode;
    private String fromHeadImage;
    private String message;
    private Timestamp messageTime;
    private String showTime;

}
