/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.beans.support;

import com.tabuyos.spring.framework.beans.config.BeanDefinition;
import com.tabuyos.spring.framework.context.support.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author tabuyos
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

  /**
   * 存储注册信息的 map
   */
  protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
