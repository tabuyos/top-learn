/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.beans.config;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanDefinition {
  private String beanClassName;
  private boolean lazyInit = false;
  private String factoryBeanName;

  public String getBeanClassName() {
    return beanClassName;
  }

  public void setBeanClassName(String beanClassName) {
    this.beanClassName = beanClassName;
  }

  public boolean isLazyInit() {
    return lazyInit;
  }

  public void setLazyInit(boolean lazyInit) {
    this.lazyInit = lazyInit;
  }

  public String getFactoryBeanName() {
    return factoryBeanName;
  }

  public void setFactoryBeanName(String factoryBeanName) {
    this.factoryBeanName = factoryBeanName;
  }

  @Override
  public String toString() {
    return "BeanDefinition{" +
        "beanClassName='" + beanClassName + '\'' +
        ", lazyInit=" + lazyInit +
        ", factoryBeanName='" + factoryBeanName + '\'' +
        '}';
  }
}
