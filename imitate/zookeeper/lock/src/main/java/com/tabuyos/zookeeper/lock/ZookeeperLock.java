/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.zookeeper.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author tabuyos
 */
public class ZookeeperLock {

  private String server = "127.0.0.1:2181";
  private ZkClient zkClient;
  private static final String rootPath = "/tabuyos-lock";

  public ZookeeperLock() {
    zkClient = new ZkClient(server, 5000, 20000);
    buildRoot();
  }

  private void buildRoot() {
    if (!zkClient.exists(rootPath)) {
      zkClient.createPersistent(rootPath);
    }
  }

  /**
   * get lock
   *
   * @param lockId  lock id
   * @param timeout timeout
   * @return Lock
   */
  public Lock lock(String lockId, long timeout) {
    // 创建临时节点
    Lock lockNode = createLockNode(lockId);
    // 尝试激活锁
    lockNode = tryActiveLock(lockNode);
    if (!lockNode.isActive()) {
      try {
        synchronized (lockNode) {
          // 如果没有激活, 则将这个线程锁住
          lockNode.wait(timeout);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (!lockNode.isActive()) {
      throw new RuntimeException("lock timeout");
    }
    return lockNode;
  }

  /**
   * release lock
   *
   * @param lock lock
   */
  public void unlock(Lock lock) {
    if (lock.isActive()) {
      zkClient.delete(lock.getPath());
    }
  }

  /**
   * try active lock
   *
   * @param lockNode lock node
   * @return Lock
   */
  private Lock tryActiveLock(Lock lockNode) {
    List<String> list = zkClient.getChildren(rootPath)
        .stream()
        .sorted()
        .map(p -> rootPath + "/" + p)
        .collect(Collectors.toList());
    String firstNodePath = list.get(0);
    if (firstNodePath.equalsIgnoreCase(lockNode.getPath())) {
      lockNode.setActive(true);
    } else {
      String upNodePath = list.get(list.indexOf(lockNode.getPath()) - 1);
      zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {

        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
          // 事件处理与心跳在同一个线程, 如果Debug时占用太多的时间, 将导致本节点被删除, 从而影响逻辑
          System.out.println("delete node: " + dataPath);
          // 当监听的上一个节点被删除了, 尝试激活自己
          Lock lock = tryActiveLock(lockNode);
          synchronized (lockNode) {
            if (lock.isActive()) {
              // 释放锁
              lockNode.notify();
            }
          }
          zkClient.unsubscribeDataChanges(upNodePath, this);
        }
      });
    }
    return lockNode;
  }

  /**
   * create ephemeral sequential node
   *
   * @param lockId lock id
   * @return Lock
   */
  private Lock createLockNode(String lockId) {
    String nodePath = zkClient.createEphemeralSequential(rootPath + "/" + lockId, "w");
    return new Lock(lockId, nodePath);
  }
}
