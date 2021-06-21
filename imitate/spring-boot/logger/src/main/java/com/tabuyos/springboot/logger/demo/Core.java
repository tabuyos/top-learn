/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.logger.demo;

import com.tabuyos.springboot.core.TabuyosCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TODO
 *
 * @author tabuyos
 */
@SpringBootApplication
public class Core {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Core.class, args);
    String tabuyos = context.getBean(TabuyosCore.class).sayHello("Tabuyos");
    System.out.println(tabuyos);
    // 当没有 com.tabuyos.springboot.logger.Tabuyos 这个类的时候, 会抛出以下异常:
    // No qualifying bean of type 'com.tabuyos.springboot.core.TabuyosCore' available
    // 创建 Tabuyos 类后, 输出正常:
    // call the sayHello method, name:Tabuyos
    // Hello Tabuyos

    // 停止容器
    context.stop();
  }
}
