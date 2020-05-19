package com.celada.adapter.in.kafka;

import com.celada.domain.EventUseCase;
import com.celada.domain.entity.Model;
import com.celada.domain.exception.ModelException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

  private final EventUseCase eventUseCase;
  private final EventMapper eventMapper;

  @KafkaListener(topics = "${kafka.topic.request}")
  @SendTo
  public Event listen(ConsumerRecord<String, Event> record)
      throws IOException, ModelException {
    log.info("Consumer | Event received: {}", record.value());
    if (EventType.CREATE.equals(record.value().getType())) {
      create(record);
    }
    if (EventType.READ.equals(record.value().getType())) {
      read(record);
    }
    return record.value();
  }

  private void read(ConsumerRecord<String, Event> record) throws IOException, ModelException {
    Model request = eventMapper.execute(record.value().getBody());
    Model model = eventUseCase.read(request.getId());
    record.value().setBody(eventMapper.execute(model));
    log.info("Sending event: {}", record.value());
  }

  private void create(ConsumerRecord<String, Event> record) throws IOException {
    Model model = eventMapper.execute(record.value().getBody());
    eventUseCase.create(model);
    log.info("Sending event: {}", record.value());
  }

}
