package com.smartarch.message.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.smartarch.model.Message;

public interface MessageMapper {
	Message getById(@Param("id") String id);
    
    void insertMessage(@Param("id") String id, @Param("topic") String topic, @Param("msg") String msg, @Param("sendTime") Timestamp sendTime);
    
}
