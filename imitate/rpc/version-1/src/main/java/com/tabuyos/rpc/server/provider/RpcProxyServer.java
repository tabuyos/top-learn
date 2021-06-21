/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Rpc proxy serve
 *
 * @author tabuyos
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
public class RpcProxyServer {

  ExecutorService executor = Executors.newCachedThreadPool();

  public void publisher(Object service, int port) {
    try (ServerSocket server = new ServerSocket(port)) {
      // 轮询模式
      while (true) {
        // BIO 阻塞在这里
        Socket socket = server.accept();
        // 执行处理
        ProcessorHandler handler = new ProcessorHandler(socket, service);
        executor.execute(handler::handle);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
