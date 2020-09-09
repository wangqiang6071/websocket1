package com.wayne.websocket;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class WebsocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
}