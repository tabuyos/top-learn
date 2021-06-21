/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.apache.kafka.callback;

import com.tabuyos.apache.kafka.constants.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * kafka 消费者
 *
 * @author tabuyos
 */
public class CallbackKafkaConsumer {

  public static void main(String[] args) {
    // 消费者配置
    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
    // 实际的消费者
    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
      consumer.subscribe(Collections.singletonList(KafkaConstant.HELLO_TOPIC));
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
        for (ConsumerRecord<String, String> record : records) {
          System.out.printf(
              "topics: %s, partition: %d, offset: %d, key: %s, value: %s%n",
              record.topic(), record.partition(), record.offset(), record.key(), record.value());
        }
      }
    }
  }
}
