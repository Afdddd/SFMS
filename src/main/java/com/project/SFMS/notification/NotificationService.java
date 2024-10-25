package com.project.SFMS.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.SFMS.config.RabbitConfig;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final SlackNotificationService slackNotificationService;
  private final EmailNotificationService emailNotificationService;

  private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 파싱용
  private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

  @Autowired
  public NotificationService(SlackNotificationService slackNotificationService,
      EmailNotificationService emailNotificationService){
    this.slackNotificationService = slackNotificationService;
    this.emailNotificationService = emailNotificationService;
  }

  @RabbitListener(queues = {"sfms.product.production.queue","sfms.product.defective_removal.queue",
      "sfms.product.packaging.queue", "sfms.box.defective_removal.queue", "sfms.box.packaging.queue",
      "sfms.shipment.queue"})
  public void sendMessage(String message){
    try{
      Map<String, Object> messageData = objectMapper.readValue(message, Map.class);

      // 각 필드를 추출
      String timestamp = (String) messageData.get("timestamp");
      String event = (String) messageData.get("event");
      String status = (String) messageData.get("status");
      double temperature = (double) messageData.get("temperature");
      double humidity = (double) messageData.get("humidity");

      String sendMessage = String.format(
          "Timestamp: %s\nEvent: %s\nStatus: %s\nTemperature: %.1f°C\nHumidity: %.1f%%",
          timestamp, event, status, temperature, humidity);

      slackNotificationService.sendSlackMessage(sendMessage);
      emailNotificationService.sendEmail(sendMessage);

    }catch (Exception e){
      logger.error(e.getMessage());
    }

  }

}
