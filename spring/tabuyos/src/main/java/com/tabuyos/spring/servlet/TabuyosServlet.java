/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * TODO
 *
 * @author tabuyos
 */
public class TabuyosServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    System.out.println("init ...");
  }
}
