package com.example.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.model.User;

@RestController
@RequestMapping("kafka")
public class UserController {

	@Autowired
	KafkaTemplate<String, String> stringKafkaTemplate;

	@Autowired
	KafkaTemplate<String, User> userKafkaTemplate;

	private static final String STRING_TOPIC = "Kafka_Example";
	private static final String USER_TOPIC = "Kafka_Example_Json";

	@GetMapping("/publish/{message}")
	public String publishString(@PathVariable final String message) {
		stringKafkaTemplate.send(STRING_TOPIC, message);
		return "published successfully!!";
	}

	@PostMapping("/publish")
	public String publishObject(@RequestBody final User user) {
		userKafkaTemplate.send(USER_TOPIC, user);
		return "published successfully!!";
	}
}
