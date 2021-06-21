/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.autoconfiguration;

import com.tabuyos.springboot.starter.format.format.FormatProcessor;
import com.tabuyos.springboot.starter.format.format.JsonFormatProcessor;
import com.tabuyos.springboot.starter.format.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author tabuyos
 */
@Configuration
public class FormatAutoConfiguration {

  @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
  @Bean
  public FormatProcessor stringFormat() {
    return new StringFormatProcessor();
  }

  @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
  @Bean
  public FormatProcessor jsonFormat() {
    return new JsonFormatProcessor();
  }
}
