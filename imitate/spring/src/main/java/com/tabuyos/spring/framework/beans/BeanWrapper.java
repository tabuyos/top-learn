/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.beans;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanWrapper {

  private Object wrappedInstance;
  private Class<?> wrappedClass;

  public BeanWrapper(Object wrappedInstance) {
    this.wrappedInstance = wrappedInstance;
    wrappedClass = this.wrappedInstance.getClass();
  }

  public Object getWrappedInstance() {
    return wrappedInstance;
  }

  /**
   * 返回代理后的 class
   * 可能会是$Proxy0
   *
   * @return 代理的 class
   */
  public Class<?> getWrappedClass() {
    return wrappedClass;
  }
}
