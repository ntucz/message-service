package com.smartarch.message.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartarch.message.MessageApplication;
import com.smartarch.message.kafka.KafkaProducer;
import com.smartarch.model.Message;

@SpringBootTest(classes = MessageApplication.class)
public class MessageApplicationTests {

	@Autowired
	private KafkaProducer<Message> kafkaSender;

	@Test
	public void kafkaSend() throws InterruptedException {
		//模拟发消息
		for (int i = 0; i < 6; i++) {
			Message user = new Message();
			user.setId(UUID.randomUUID().toString());
			user.setMsg("test msg "+i);
			user.setSendTime(new Timestamp(new Date().getTime()));

			kafkaSender.send(user);
		}
		Thread.sleep(10000L);
	}

}
