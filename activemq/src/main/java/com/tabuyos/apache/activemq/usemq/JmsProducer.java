/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.apache.activemq.usemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者
 *
 * @author tabuyos
 */
public class JmsProducer {

  /** 默认用户名 */
  private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
  /** 默认密码 */
  private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
  /** 默认连接地址 */
  private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
  /** 重试次数 */
  private static final int SENDNUM = 3;

  public static void main(String[] args) {
    String topicName = "HelloActiveMQTopic";
    String queueName = "HelloActiveMQQueue";
    // 连接工厂
    ConnectionFactory connectionFactory;
    // 连接
    Connection connection = null;
    // 会话
    Session session;
    // 消息的目的地
    Destination destination;
    // 消息生产者
    MessageProducer messageProducer;
    // 实例化
    connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

    try {
      // 获取连接
      connection = connectionFactory.createConnection();
      // 启动
      connection.start();
      // 获取会话
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      // 创建目的地
      destination = session.createTopic(topicName);
      // destination = session.createQueue(queueName);
      // 创建生产者
      messageProducer = session.createProducer(destination);
      for (int i = 0; i < SENDNUM; i++) {
        String msg = i + "-" + System.currentTimeMillis();
        TextMessage message = session.createTextMessage(msg);
        System.out.println("message: " + msg);
        messageProducer.send(message);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
