/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author tabuyos
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
@Component
public class RpcProxyServer implements ApplicationContextAware, InitializingBean {

  ExecutorService executor = Executors.newCachedThreadPool();

  private final Map<String, Object> handleMap = new ConcurrentHashMap<>();
  private final int port;

  public RpcProxyServer(int port) {
    this.port = port;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    try (ServerSocket server = new ServerSocket(port)) {
      while (true) {
        Socket socket = server.accept();
        ProcessorHandler handler = new ProcessorHandler(socket, handleMap);
        executor.execute(handler::handle);
      }
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
    serviceBeanMap.forEach((key, value) -> {
      RpcService rpcService = value.getClass().getAnnotation(RpcService.class);
      String serviceName = rpcService.value().getName();
      String version = rpcService.version();
      if (StringUtils.hasText(version)) {
        serviceName += "-" + version;
      }
      handleMap.put(serviceName, value);
    });
  }
}
