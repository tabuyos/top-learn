/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework.beans.config;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanDefinition {

  private String beanClassName;
  private boolean lazyInit = false;
  private String factroyBeanName;

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

  public String getFactroyBeanName() {
    return factroyBeanName;
  }

  public void setFactroyBeanName(String factroyBeanName) {
    this.factroyBeanName = factroyBeanName;
  }

  @Override
  public String toString() {
    return "BeanDefinition{" +
        "beanClassName='" + beanClassName + '\'' +
        ", lazyInit=" + lazyInit +
        ", factroyBeanName='" + factroyBeanName + '\'' +
        '}';
  }
}
