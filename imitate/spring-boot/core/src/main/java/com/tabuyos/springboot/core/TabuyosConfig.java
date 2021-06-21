/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author tabuyos
 */
@Configuration
public class TabuyosConfig {

  @Bean
  public TabuyosCore tabuyosCore() {
    return new TabuyosCore();
  }
}
