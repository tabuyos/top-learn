/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.tomcat.nio.servlet;

import com.tabuyos.tomcat.nio.http.Request;
import com.tabuyos.tomcat.nio.http.Response;
import com.tabuyos.tomcat.nio.http.TabuyosServlet;

/**
 * TODO
 *
 * @author tabuyos
 */
public class SecondServlet extends TabuyosServlet {

  @Override
  protected void doGet(Request request, Response response) throws Exception {
    this.doPost(request, response);
  }

  @Override
  protected void doPost(Request request, Response response) throws Exception {
    response.write("This is Second servlet");
  }
}
