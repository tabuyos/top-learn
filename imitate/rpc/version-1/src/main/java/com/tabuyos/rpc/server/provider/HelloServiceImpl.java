/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IHelloService;
import com.tabuyos.rpc.server.api.User;

/**
 * implementation for Hello service
 *
 * @author tabuyos
 */
public class HelloServiceImpl implements IHelloService {
  @Override
  public String sayHello(String name) {
    System.out.println("call the sayHello method, name: " + name);
    return "Hello: " + name;
  }

  @Override
  public String saveUser(User user) {
    System.out.println("call the saveUser method, user: " + user);
    return "success for save user";
  }
}
