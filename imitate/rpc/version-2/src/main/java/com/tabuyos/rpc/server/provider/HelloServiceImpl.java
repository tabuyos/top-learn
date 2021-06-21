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
@RpcService(value = IHelloService.class, version = "V1.0")
public class HelloServiceImpl implements IHelloService {
  @Override
  public String sayHello(String name) {
    System.out.println("[V1.0] call the sayHello method, Hello: " + name);
    return "[V1.0] Hello: " + name;
  }

  @Override
  public String saveUser(User user) {
    System.out.println("[V1.0] call the saveUser method, user: " + user);
    return "[V1.0] success for save user";
  }
}
