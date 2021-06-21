/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import com.tabuyos.rpc.server.api.IHelloService;
import com.tabuyos.rpc.server.api.IPaymentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Version2Consumer {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    RpcProxyClient client = context.getBean(RpcProxyClient.class);
    // IPaymentService paymentService = client.clientProxy(IPaymentService.class, "localhost", 8080);
    // paymentService.doPay();
    IHelloService helloService = client.clientProxy(IHelloService.class, "localhost", 8080);
    System.out.println(helloService.sayHello("tabuyos"));
  }
}
