/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.zookeeper.lock;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Lock {

  private String lockId;
  private String path;
  private boolean active;

  public Lock() {
  }

  public Lock(String lockId, String path) {
    this.lockId = lockId;
    this.path = path;
  }

  public String getLockId() {
    return lockId;
  }

  public void setLockId(String lockId) {
    this.lockId = lockId;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Lock{" +
        "lockId='" + lockId + '\'' +
        ", path='" + path + '\'' +
        ", active=" + active +
        '}';
  }
}
