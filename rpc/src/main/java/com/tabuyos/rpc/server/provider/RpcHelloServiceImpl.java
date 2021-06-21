/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IRpcHelloService;

/**
 * TODO
 *
 * @author tabuyos
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
  @Override
  public String hello(String name) {
    return "Hello" + name;
  }
}
