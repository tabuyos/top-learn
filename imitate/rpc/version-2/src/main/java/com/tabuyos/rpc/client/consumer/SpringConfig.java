/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author tabuyos
 */
@Configuration
public class SpringConfig {

  @Bean
  public RpcProxyClient rpcProxyClient() {
    return new RpcProxyClient();
  }
}
