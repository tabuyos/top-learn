package com.tabuyos.rpc.server.api;

/**
 * Hello service interface
 *
 * @author tabuyos
 */
public interface IHelloService {

  String sayHello(String name);

  String saveUser(User user);
}
