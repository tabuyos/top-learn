/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.logger;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * TODO
 *
 * @author tabuyos
 */
public class CacheImportSelector implements ImportSelector {
  /**
   * @param importingClassMetadata 启动类的上的所有注解元信息
   * @return class array
   */
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    // AnnotationMetadata 是注解启动类的上的注解元信息
    Map<String, Object> attributes =
        importingClassMetadata.getAnnotationAttributes(EnableDefineService.class.getName());
    // 得到的是该注解的属性(eg: value(), exclude() etc.)
    System.out.println("attributes: " + attributes);
    // 动态的注入 bean, 自己可以实现判断, 从而动态的注入
    return new String[]{CacheService.class.getName()};
  }
}
