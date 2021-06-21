/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format;

import com.tabuyos.springboot.starter.format.autoconfiguration.HelloProperties;
import com.tabuyos.springboot.starter.format.format.FormatProcessor;

/**
 * TODO
 *
 * @author tabuyos
 */
public class HelloFormatTemplate {

  private final HelloProperties helloProperties;
  private final FormatProcessor formatProcessor;

  public HelloFormatTemplate(HelloProperties helloProperties, FormatProcessor formatProcessor) {
    this.helloProperties = helloProperties;
    this.formatProcessor = formatProcessor;
  }

  public <T> String doFormat(T object) {
    StringBuilder builder = new StringBuilder();
    builder.append("begin: Execute format")
        .append("<br/>")
        .append("HelloProperties: ")
        .append(formatProcessor.format(helloProperties.getInfo()))
        .append("<br/>")
        .append("Object format result: ")
        .append(formatProcessor.format(object))
        .append("<br/>");
    return builder.toString();
  }
}
