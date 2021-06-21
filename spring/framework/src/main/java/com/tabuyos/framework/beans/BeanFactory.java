package com.tabuyos.framework.beans;

/**
 * TODO
 *
 * @author tabuyos
 */
public interface BeanFactory {

  /**
   * singleton
   * @param beanName bean name
   * @return object
   */
  Object getBean(String beanName);
}
