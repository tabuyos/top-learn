/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.apache.kafka.hello;

import com.tabuyos.apache.kafka.constants.KafkaConstant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * kafka 生产者
 *
 * @author tabuyos
 */
public class HelloKafkaProducer {

  public static void main(String[] args) {
    // 生产者配置
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    // 实际的生产者
    try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
      // 构建我们的消息本身
      ProducerRecord<String, String> record;
      record = new ProducerRecord<>(KafkaConstant.HELLO_TOPIC, "teacher-0", "tabuyos");
      producer.send(record);
      System.out.println("message is sent.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
