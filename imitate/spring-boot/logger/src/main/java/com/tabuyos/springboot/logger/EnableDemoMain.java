/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TODO
 *
 * @author tabuyos
 */
@SpringBootApplication
@EnableDefineService
@EnableTabuyos
public class EnableDemoMain {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(EnableDemoMain.class, args);
    System.out.println(context.getBean(CacheService.class));
    System.out.println(context.getBean(LoggerService.class));

    context.stop();
  }
}
