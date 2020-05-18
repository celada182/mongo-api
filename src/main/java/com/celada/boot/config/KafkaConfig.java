package com.celada.boot.config;

import com.celada.adapter.in.kafka.EventMapper;
import com.celada.adapter.in.kafka.KafkaConsumer;
import com.celada.domain.ModelUseCase;
import com.celada.kafka.Event;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.topic.response}")
  private String responseTopic;

  @Value("${kafka.group}")
  private String group;

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    return objectMapper;
  }

  @Bean
  public EventMapper eventMapper(ObjectMapper objectMapper) {
    return new EventMapper(objectMapper);
  }

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return props;
  }

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
    return props;
  }

  @Bean
  public ProducerFactory<String, Event> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Event> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ReplyingKafkaTemplate<String, Event, Event> replyKafkaTemplate(
      ProducerFactory<String, Event> pf, KafkaMessageListenerContainer<String, Event> container) {
    return new ReplyingKafkaTemplate<>(pf, container);

  }

  @Bean
  public KafkaMessageListenerContainer<String, Event> replyContainer(ConsumerFactory<String, Event> cf) {
    ContainerProperties containerProperties = new ContainerProperties(responseTopic);
    return new KafkaMessageListenerContainer<>(cf, containerProperties);
  }

  @Bean
  public ConsumerFactory<String, Event> consumerFactory() {
    JsonDeserializer<Event> deserializer = new JsonDeserializer<>(Event.class, new ObjectMapper());
    deserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Event>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setReplyTemplate(kafkaTemplate());
    return factory;
  }

  @Bean
  public KafkaConsumer replyingKafkaConsumer(ModelUseCase modelUseCase, EventMapper eventMapper) {
    return new KafkaConsumer(modelUseCase, eventMapper);
  }

}

