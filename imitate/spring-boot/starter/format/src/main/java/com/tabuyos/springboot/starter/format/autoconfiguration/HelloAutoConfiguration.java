/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.autoconfiguration;

import com.tabuyos.springboot.starter.format.HelloFormatTemplate;
import com.tabuyos.springboot.starter.format.format.FormatProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TODO
 *
 * @author tabuyos
 */
@Import(FormatAutoConfiguration.class)
@EnableConfigurationProperties({HelloProperties.class})
@Configuration
public class HelloAutoConfiguration {

  @Bean
  public HelloFormatTemplate helloFormatTemplate(HelloProperties properties, FormatProcessor formatProcessor) {
    return new HelloFormatTemplate(properties, formatProcessor);
  }
}
