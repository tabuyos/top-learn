/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.apache.activemq.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Active MQ 启动类
 *
 * @author tabuyos
 */
@SpringBootApplication(scanBasePackages = {"com.tabuyos.apache.activemq"})
public class ActiveMqApplication {
  public static void main(String[] args) {
    SpringApplication.run(ActiveMqApplication.class, args);
  }
}
