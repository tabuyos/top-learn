/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * handle request
 *
 * @author tabuyos
 */
public class ProcessorHandler {

  private final Socket socket;
  private final Map<String, Object> handleMap;

  public ProcessorHandler(Socket socket, Map<String, Object> handleMap) {
    this.socket = socket;
    this.handleMap = handleMap;
  }

  public void handle() {
    // server: 先创建input, 再创建output
    // client: 先创建output, 再创建input
    try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
      RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
      // 反射调用本地服务
      Object result = invoke(rpcRequest);
      System.out.println("result: " + result);
      objectOutputStream.writeObject(result);
      objectOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    System.out.println("get request: " + request);
    String serviceName = request.getClassName();
    String version = request.getVersion();
    if (StringUtils.hasText(version)) {
      serviceName += "-" + version;
    }
    System.out.println(handleMap.keySet());
    System.out.println(serviceName);
    Object service = handleMap.get(serviceName);
    if (service == null) {
      throw new RuntimeException("service not found: " + serviceName);
    }

    // 请求参数
    Object[] args = request.getParameters();
    Method method;
    if (args != null) {
      // 参数类型
      Class<?>[] types = new Class[args.length];
      for (int i = 0; i < args.length; i++) {
        types[i] = args[i].getClass();
      }
      // 加载对应的类和方法
      Class<?> clazz = Class.forName(request.getClassName());
      method = clazz.getMethod(request.getMethodName(), types);
    } else {
      // 加载对应的类和方法
      Class<?> clazz = Class.forName(request.getClassName());
      method = clazz.getMethod(request.getMethodName());
    }
    return method.invoke(service, args);
  }
}
