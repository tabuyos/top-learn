/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import com.tabuyos.rpc.server.api.IHelloService;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Version1Consumer {

  public static void main(String[] args) {
    RpcProxyClient client = new RpcProxyClient();
    IHelloService service = client.clientProxy(IHelloService.class, "localhost", 8080);
    String tabuyos = service.sayHello("tabuyos");
    System.out.println("tabuyos: " + tabuyos);
  }
}
