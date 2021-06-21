/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.tomcat.nio;

import com.tabuyos.tomcat.nio.http.Request;
import com.tabuyos.tomcat.nio.http.Response;
import com.tabuyos.tomcat.nio.http.TabuyosServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Tomcat {

  private final int port = 8080;
  private ServerSocket server;
  private final Map<String, TabuyosServlet> servletMap = new ConcurrentHashMap<>();

  private final Properties properties = new Properties();

  /**
   * 初始化
   */
  private void init() {
    // 加载 properties 文件, 同时初始化 servlet mapping 对象
    try {
      InputStream inputStream = this.getClass().getResourceAsStream("/web.properties");
      properties.load(inputStream);
      properties.forEach((key, value) -> {
        String keyString = key.toString();
        if (keyString.endsWith(".url")) {
          String servletName = keyString.replaceAll("\\.url$", "");
          String url = properties.getProperty(keyString);
          String className = properties.getProperty(servletName + ".className");
          // 单实例, 多线程
          TabuyosServlet tabuyosServlet = null;
          try {
            tabuyosServlet = (TabuyosServlet) Class.forName(className).newInstance();
          } catch (Exception e) {
            e.printStackTrace();
          }
          servletMap.put(url, tabuyosServlet);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
    // 加载配置文件, 初始化mapping
    init();

    try {
      server = new ServerSocket(this.port);
      System.out.println("Tomcat startup on " + this.port);
      // 等待用户请求
      while (true) {
        Socket socket = server.accept();
        process(socket);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void process(Socket socket) throws Exception {
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    Request request = new Request(inputStream);
    Response response = new Response(outputStream);
    String url = request.getUrl();
    System.out.println("url: " + url);
    if (servletMap.containsKey(url)) {
      servletMap.get(url).service(request, response);
    } else {
      response.write("404 - Not Found");
    }

    outputStream.flush();
    outputStream.close();

    inputStream.close();
    socket.close();
  }

  public static void main(String[] args) {
    new Tomcat().start();
  }
}
