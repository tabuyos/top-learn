/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.api;

/**
 * service interface
 *
 * @author tabuyos
 */
public interface IRpcService {

  int add(int a, int b);

  int sub(int a, int b);

  int mult(int a, int b);

  int div(int a, int b);
}
