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

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class ReplyingKafkaConsumer {

  private final ModelUseCase modelUseCase;
  private final EventMapper eventMapper;

  @KafkaListener(topics = "${kafka.topic.request}")
  @SendTo
  public Event listen(Event event) throws IOException {
    log.info("Consumer | Event received: {}", event);
    if (EventType.CREATE.equals(event.getType())) {
      Model model = eventMapper.execute(event.getBody());
      modelUseCase.create(model);
      return event;
    }
    throw new InvalidEventException("Invalid type");
  }

}
