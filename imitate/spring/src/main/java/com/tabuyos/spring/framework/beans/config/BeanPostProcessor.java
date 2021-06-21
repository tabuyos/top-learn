/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.beans.config;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanPostProcessor {

  public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
    return bean;
  }

  public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
    return bean;
  }
}
