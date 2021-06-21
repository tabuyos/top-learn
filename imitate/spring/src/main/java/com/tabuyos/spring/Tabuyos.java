/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring;

import com.tabuyos.spring.action.MyAction;
import com.tabuyos.spring.framework.context.ApplicationContext;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Tabuyos {

  public static void main(String[] args) {
    ApplicationContext context = new ApplicationContext("classpath:application.properties");
    try {
      MyAction myAction = context.getBean(MyAction.class);
      System.out.println(myAction);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
