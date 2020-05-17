package com.celada.adapter.in.kafka;

import com.celada.domain.ModelUseCase;
import com.celada.domain.entity.Model;
import com.celada.kafka.Event;
import com.celada.kafka.EventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ReplyingKafkaConsumer {

  private final ModelUseCase modelUseCase;

  @KafkaListener(topics = "${kafka.topic.request}")
  @SendTo
  public Event listen(Event event) {
    log.info("Consumer | Event received: {}", event);
    if (EventType.CREATE.equals(event.getType())) {
      Model model = EventMapper.execute(event.getBody());
      modelUseCase.create(model);
      return event;
    }
    throw new InvalidEventException("Invalid type");
  }

}
