/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author tabuyos
 */
@SuppressWarnings("unchecked")
public class RpcProxyClient {

  public <T> T clientProxy(final Class<T> clazz, final String host, int port) {
    RemoteInvocationHandler handler = new RemoteInvocationHandler(host, port);
    return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler::handle);
  }
}
