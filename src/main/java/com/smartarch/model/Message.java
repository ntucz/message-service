package com.smartarch.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Message {
    private String id;
    private String topic;
    private String msg;
    private Timestamp sendTime;
}
