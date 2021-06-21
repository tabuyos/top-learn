/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IHelloService;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Version1Provider {

  public static void main(String[] args) {
    IHelloService service = new HelloServiceImpl();
    RpcProxyServer server = new RpcProxyServer();
    server.publisher(service, 8080);
  }
}
