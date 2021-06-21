/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * TODO
 *
 * @author tabuyos
 */
@ConfigurationProperties(prefix = HelloProperties.HELLO_FORMAT_PREFIX)
public class HelloProperties {
  public static final String HELLO_FORMAT_PREFIX = "tabuyos.hello.format";
  private Map<String, Object> info;

  public Map<String, Object> getInfo() {
    return info;
  }

  public void setInfo(Map<String, Object> info) {
    this.info = info;
  }
}
