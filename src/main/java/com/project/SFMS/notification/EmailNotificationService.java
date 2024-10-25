package com.project.SFMS.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {

  private final JavaMailSender mailSender;

  public void sendEmail(String message){
    MimeMessage mimeMessage = mailSender.createMimeMessage();

    String toEmail = "kim41516@naver.com";
    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String subject = "[SFMS] " + currentTime; // 제목에 현재 시간을 추가



    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      helper.setTo(toEmail);
      helper.setSubject(subject);
      helper.setText(message, false);

      mailSender.send(mimeMessage);

    } catch (MessagingException e) {
      log.error(e.getMessage());
    }
  }

}
