package com.tabuyos.zookeeper.lock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author tabuyos
 */
class LockTest {

  ZookeeperLock zookeeperLock;
  static Long count = 0L;

  @BeforeEach
  void setUp() {
    zookeeperLock = new ZookeeperLock();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  public void getLockTest() throws Exception {
    Lock lock = zookeeperLock.lock("doudou", 60 * 1000);
    System.out.println("get lock success");
    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    assert lock != null;
  }

  @Test
  public void run() throws Exception {
    File file = new File("temp/test.txt");
    if (!file.exists()) {
      boolean mkdirs = file.getParentFile().mkdirs();
      boolean newFile = file.createNewFile();
    }
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0; i < 100; i++) {
      executor.submit(() -> {
        Lock lock = zookeeperLock.lock("count-number-", 60 * 1000);
        try {
          String firstLine = Files.lines(file.toPath()).findFirst().orElse("0");
          int count = Integer.parseInt(firstLine);
          count++;
          Files.write(file.toPath(), String.valueOf(count).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          zookeeperLock.unlock(lock);
        }
      });
    }
    executor.shutdown();
    executor.awaitTermination(60, TimeUnit.SECONDS);
    String firstLine = Files.lines(file.toPath()).findFirst().orElse("0");
    System.out.println(firstLine);
  }
}