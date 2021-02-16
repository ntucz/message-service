package com.smartarch.message.kafka;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartarch.message.mapper.MessageMapper;
import com.smartarch.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer<T> {
	
	@Autowired
    private MessageMapper msgMapper;

	@KafkaListener(groupId="crisschen", topics = "smartarch", errorHandler = "consumeErrorHandler")
    public void listen(List<ConsumerRecord<?, ?>> records) {
		if(records!=null && !records.isEmpty()) {
			log.info("records size:{}", records.size());
			for(ConsumerRecord<?, ?> record : records) {
				Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		        if (kafkaMessage.isPresent()) {
		            //获取消息
		            Object message = kafkaMessage.get();
		            log.info("Receive： +++++++++++++++ Record:" + record);
		            try {
		            	JSONObject json = JSONObject.parseObject((String)message);
		            	Message msg = JSON.toJavaObject(json,Message.class);
		            	msgMapper.insertMessage(msg.getId(), msg.getTopic(), msg.getMsg(), msg.getSendTime());
		            } catch (Exception e) {
		            	log.error("process messge error:", e);
		            	throw e;
		            }
		        }
			}
		}
    }

	@Bean
	public ConsumerAwareListenerErrorHandler consumeErrorHandler() {
	    return (message, exception, consumer) -> {
	        log.error("Consume message:{}, error:{}", message.getPayload(), exception.toString());
	        return null;
	    };
	}
}
