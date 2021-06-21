/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.tomcat.netty.http;

import java.io.InputStream;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Request {

  private String method;
  private String url;

  public Request(InputStream inputStream) {
    try {
      String content = "";
      byte[] bytes = new byte[1024];
      int length;
      if ((length = inputStream.read(bytes)) > 0) {
        content = new String(bytes, 0, length);
      }
      String line = content.split("\\n")[0];
      String[] els = line.split("\\s");
      this.method = els[0];
      this.url = els[1].split("\\?")[0];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getMethod() {
    return method;
  }

  public String getUrl() {
    return url;
  }
}
