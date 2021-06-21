/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author tabuyos
 */
@Configuration
@ComponentScan(basePackages = {"com.tabuyos.rpc"})
public class SpringConfig {

  @Bean
  public RpcProxyServer rpcProxyServer() {
    return new RpcProxyServer(8080);
  }
}
