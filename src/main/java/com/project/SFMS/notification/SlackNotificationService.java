package com.project.SFMS.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackNotificationService {

  @Value("${slack.webhook-url}")
  private String slackWebHookUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public void sendSlackMessage(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String payload = "{\"text\":\"" + message.replace("\"", "\\\"") + "\"}";

    HttpEntity<String> entity = new HttpEntity<>(payload, headers);

    restTemplate.postForEntity(slackWebHookUrl, entity, String.class);
  }

}
