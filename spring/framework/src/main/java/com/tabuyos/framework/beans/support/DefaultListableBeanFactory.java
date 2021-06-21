/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework.beans.support;

import com.tabuyos.framework.beans.config.BeanDefinition;
import com.tabuyos.framework.context.support.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author tabuyos
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
