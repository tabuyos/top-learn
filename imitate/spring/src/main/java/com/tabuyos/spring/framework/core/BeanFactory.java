/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.core;

/**
 * TODO
 *
 * @author tabuyos
 */
public interface BeanFactory {

  Object getBean(String beanName) throws Exception;
  <T> T getBean(Class<T> beanClass) throws Exception;
}
