/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.tomcat.nio.http;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Response {

  private final OutputStream outputStream;

  public Response(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void write(String content) throws Exception {
    // 因为用到的是HTTP协议, 所以我们应该遵循HTTP协议规则
    StringBuilder builder = new StringBuilder();
    builder.append("HTTP/1.1 200 OK")
        .append("\n")
        .append("Content-Type: text/html;")
        .append("\n")
        .append("\r\n")
        .append(content);
    outputStream.write(builder.toString().getBytes(StandardCharsets.UTF_8));
  }
}
