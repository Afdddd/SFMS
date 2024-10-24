package com.project.SFMS.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

  static final String TOPIC_EXCHANGE_NAME = "sfms-exchange";


  @Bean
  Queue productProductionQueue(){
    logger.info("create productProductionQueue");
    return new Queue("sfms.product.production.queue",true);
  }

  @Bean
  Queue productDefectiveRemovalQueue(){
    logger.info("create productDefectiveRemovalQueue");
    return new Queue("sfms.product.defective_removal.queue",true);
  }

  @Bean
  Queue productPackagingQueue(){
    logger.info("create productPackagingQueue");
    return new Queue("sfms.product.packaging.queue",true);
  }

  @Bean
  Queue boxDefectiveRemovalQueue() {
    logger.info("create boxDefectiveRemovalQueue");
    return new Queue("sfms.box.defective_removal.queue", true);
  }

  @Bean
  Queue boxPackagingQueue() {
    logger.info("create boxPackagingQueue");
    return new Queue("sfms.box.packaging.queue", true);
  }

  @Bean
  Queue shipmentQueue() {
    logger.info("create shipmentQueue");
    return new Queue("sfms.shipment.queue", true);
  }

  @Bean
  TopicExchange exchange(){
    return new TopicExchange(TOPIC_EXCHANGE_NAME);
  }

  @Bean
  Binding productProductionBinding(Queue productProductionQueue, TopicExchange exchange) {
    return BindingBuilder.bind(productProductionQueue).to(exchange).with("sfms.product.production");
  }

  @Bean
  Binding productDefectiveRemovalBinding(Queue productDefectiveRemovalQueue, TopicExchange exchange) {
    return BindingBuilder.bind(productDefectiveRemovalQueue).to(exchange).with("sfms.product.defective_removal");
  }

  @Bean
  Binding productPackagingBinding(Queue productPackagingQueue, TopicExchange exchange) {
    return BindingBuilder.bind(productPackagingQueue).to(exchange).with("sfms.product.packaging");
  }

  @Bean
  Binding boxDefectiveRemovalBinding(Queue boxDefectiveRemovalQueue, TopicExchange exchange) {
    return BindingBuilder.bind(boxDefectiveRemovalQueue).to(exchange).with("sfms.box.defective_removal");
  }

  @Bean
  Binding boxPackagingBinding(Queue boxPackagingQueue, TopicExchange exchange) {
    return BindingBuilder.bind(boxPackagingQueue).to(exchange).with("sfms.box.packaging");
  }

  @Bean
  Binding shipmentBinding(Queue shipmentQueue, TopicExchange exchange) {
    return BindingBuilder.bind(shipmentQueue).to(exchange).with("sfms.shipment");
  }

}
