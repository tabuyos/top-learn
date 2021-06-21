/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IHelloService;
import com.tabuyos.rpc.server.api.User;

/**
 * TODO
 *
 * @author tabuyos
 */
@RpcService(value = IHelloService.class, version = "V2.0")
public class HelloServiceImpl2 implements IHelloService {
  @Override
  public String sayHello(String name) {
    System.out.println("[V2.0] call the sayHello method, Hello: " + name);
    return "[V2.0] Hello: " + name;
  }

  @Override
  public String saveUser(User user) {
    System.out.println("[V2.0] call the saveUser method, user: " + user);
    return "[V2.0] success for save user";
  }
}
