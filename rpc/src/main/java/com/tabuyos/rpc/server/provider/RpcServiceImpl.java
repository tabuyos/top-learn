/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IRpcService;

/**
 * TODO
 *
 * @author tabuyos
 */
public class RpcServiceImpl implements IRpcService {
  @Override
  public int add(int a, int b) {
    return a + b;
  }

  @Override
  public int sub(int a, int b) {
    return a - b;
  }

  @Override
  public int mult(int a, int b) {
    return a * b;
  }

  @Override
  public int div(int a, int b) {
    return a / b;
  }
}
