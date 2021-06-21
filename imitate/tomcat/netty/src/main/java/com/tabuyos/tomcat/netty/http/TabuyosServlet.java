/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.tomcat.netty.http;

/**
 * TODO
 *
 * @author tabuyos
 */
public abstract class TabuyosServlet {
  public void service(Request request, Response response) throws Exception {
    if ("GET".equalsIgnoreCase(request.getMethod())) {
      doGet(request, response);
    } else {
      doPost(request, response);
    }
  }

  protected abstract void doGet(Request request, Response response) throws Exception;

  protected abstract void doPost(Request request, Response response) throws Exception;
}
