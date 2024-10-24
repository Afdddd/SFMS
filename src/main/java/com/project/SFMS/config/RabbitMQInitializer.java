package com.project.SFMS.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQInitializer {

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQInitializer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
    try {
      // RabbitMQ에 빈 메시지를 전송하여 연결을 강제로 시도
      rabbitTemplate.convertAndSend("sfms-exchange", "sfms.product.production", "Test connection");
      System.out.println("Successfully connected to RabbitMQ during startup.");
    } catch (Exception e) {
      System.err.println("Failed to connect to RabbitMQ: " + e.getMessage());
    }
  }
}
