/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * data test class
 *
 * @author tabuyos
 */
public class DataTest {

  ZooKeeper zooKeeper;

  @BeforeEach
  void setUp() throws IOException {
    String connection = "127.0.0.1:2181";
    zooKeeper = new ZooKeeper(connection, 4000, (event) -> {
      System.out.println(event.getPath());
      System.out.println(event);
    });
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  public void getData0() throws InterruptedException, KeeperException {
    byte[] data = zooKeeper.getData("/tabuyos", false, null);
    System.out.println(new String(data));
  }

  @Test
  public void getData1() throws InterruptedException, KeeperException {
    byte[] data = zooKeeper.getData("/tabuyos", true, null);
    System.out.println(new String(data));
    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
  }

  @Test
  public void getData2() throws InterruptedException, KeeperException {
    byte[] data = zooKeeper.getData("/tabuyos", (event) -> System.out.println(event.getPath()), null);
    System.out.println(new String(data));
    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
  }

  @Test
  public void createNode() throws InterruptedException, KeeperException {
    List<ACL> list = new ArrayList<>();
    int permission = ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ;
    ACL acl = new ACL(permission, new Id("world", "anyone"));
    list.add(acl);
    zooKeeper.create("/tabuyos/node1", "Hello node1".getBytes(StandardCharsets.UTF_8), list, CreateMode.PERSISTENT);
  }

  @Test
  public void calc() {
    int count = 3 | 7;
    System.out.println(count);
  }
}
