/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework.context;

import com.tabuyos.framework.beans.BeanFactory;
import com.tabuyos.framework.beans.config.BeanDefinition;
import com.tabuyos.framework.beans.support.BeanDefinitionReader;
import com.tabuyos.framework.beans.support.DefaultListableBeanFactory;

import java.util.List;

/**
 * TODO
 *
 * @author tabuyos
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

  private final String[] configLocations;

  public ApplicationContext(String[] configLocations) {
    this.configLocations = configLocations;
    refresh();
  }

  @Override
  protected void refresh() {
    // 1. 定位配置文件
    BeanDefinitionReader reader = new BeanDefinitionReader(configLocations);
    // 2. 加载配置文件, 扫描相关的类, 并且封装为BeanDefinition
    List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
    // 3. 注册, 把配置信息放到容器中
    doRegistryBeanDefinition(beanDefinitions);
    // 4. 将不是延迟加载的类, 提前初始化
    doAutowired();
  }

  private void doAutowired() {

  }

  private void doRegistryBeanDefinition(List<BeanDefinition> beanDefinitions) {

  }

  @Override
  public Object getBean(String beanName) {
    return null;
  }
}
