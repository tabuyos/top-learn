/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.core;

/**
 * TODO
 *
 * @author tabuyos
 */
public class TabuyosCore {

  public String sayHello(String name) {
    System.out.println("call the sayHello method, name: " + name);
    return "Hello " + name;
  }
}
