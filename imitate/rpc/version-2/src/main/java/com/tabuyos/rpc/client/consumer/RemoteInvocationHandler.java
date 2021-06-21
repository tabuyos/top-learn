/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import com.tabuyos.rpc.server.api.RpcRequest;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author tabuyos
 */
public class RemoteInvocationHandler {

  private final String host;
  private final int port;

  public RemoteInvocationHandler(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public Object handle(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("call the handle method");
    // 请求数据的包装
    RpcRequest request = new RpcRequest();
    request.setClassName(method.getDeclaringClass().getName());
    request.setMethodName(method.getName());
    request.setParameters(args);
    request.setVersion("V2.0");
    // 远程通信
    RpcNetTransport transport = new RpcNetTransport(host, port);
    return transport.send(request);
  }
}
