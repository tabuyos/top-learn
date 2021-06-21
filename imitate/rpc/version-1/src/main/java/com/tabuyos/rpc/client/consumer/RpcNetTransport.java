/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.client.consumer;

import com.tabuyos.rpc.server.api.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO
 *
 * @author tabuyos
 */
public class RpcNetTransport {

  private final String host;
  private final int port;

  public RpcNetTransport(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public Object send(RpcRequest request) {
    // server: 先创建input, 再创建output
    // client: 先创建output, 再创建input
    try (Socket socket = new Socket(host, port);
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
         ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
      objectOutputStream.writeObject(request);
      objectOutputStream.flush();
      System.out.println("send request: " + request);
      return objectInputStream.readObject();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
