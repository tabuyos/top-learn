/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.context;

import com.tabuyos.spring.framework.annotation.TabuyosAutowired;
import com.tabuyos.spring.framework.annotation.TabuyosController;
import com.tabuyos.spring.framework.annotation.TabuyosService;
import com.tabuyos.spring.framework.beans.BeanWrapper;
import com.tabuyos.spring.framework.beans.config.BeanDefinition;
import com.tabuyos.spring.framework.beans.config.BeanPostProcessor;
import com.tabuyos.spring.framework.beans.support.BeanDefinitionReader;
import com.tabuyos.spring.framework.beans.support.DefaultListableBeanFactory;
import com.tabuyos.spring.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author tabuyos
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

  private String[] configLocations;
  private BeanDefinitionReader reader;

  /**
   * 单例IOC容器
   */
  private final Map<String, Object> singletonMap = new ConcurrentHashMap<>();
  /**
   * 通用IOC容器
   */
  private final Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

  public ApplicationContext(String... configLocations) {
    this.configLocations = configLocations;
    try {
      refresh();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void refresh() throws Exception {
    // 1. 定位, 定位配置文件
    reader = new BeanDefinitionReader(this.configLocations);
    // 2. 加载配置文件, 扫描相关的类, 并封装为BeanDefinition
    List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
    // 3. 注册, 把配置信息放到容器中
    doRegisterBeanDefinition(beanDefinitions);
    // 4. 将不是延迟加载的类, 提前初始化
    doAutowired();
  }

  private void doAutowired() {
    super.beanDefinitionMap.forEach((key, value) -> {
      if (!value.isLazyInit()) {
        try {
          getBean(key);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) {
    beanDefinitions.forEach(bd -> {
      if (super.beanDefinitionMap.containsKey(bd.getFactoryBeanName())) {
        throw new RuntimeException("The " + bd.getFactoryBeanName() + "is exists");
      }
      super.beanDefinitionMap.put(bd.getFactoryBeanName(), bd);
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBean(Class<T> beanClass) throws Exception {
    return (T) getBean(beanClass.getName());
  }

  @Override
  public Object getBean(String beanName) throws Exception {
    BeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
    Object instance = null;
    BeanPostProcessor postProcessor = new BeanPostProcessor();
    postProcessor.postProcessBeforeInitialization(instance, beanName);
    instance = instantiateBean(beanName, beanDefinition);
    BeanWrapper wrapper = new BeanWrapper(instance);
    this.factoryBeanInstanceCache.put(beanName, wrapper);
    postProcessor.postProcessAfterInitialization(instance, beanName);
    // 注入
    populateBean(beanName, new BeanDefinition(), wrapper);
    return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
  }

  private void populateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper wrapper) {
    Object instance = wrapper.getWrappedInstance();
    Class<?> clazz = wrapper.getWrappedClass();
    if (!(clazz.isAnnotationPresent(TabuyosController.class) || clazz.isAnnotationPresent(TabuyosService.class))) {
      return;
    }
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (!field.isAnnotationPresent(TabuyosAutowired.class)) {
        continue;
      }
      TabuyosAutowired autowired = field.getAnnotation(TabuyosAutowired.class);
      String autowiredBeanName = autowired.value().trim();
      if ("".equalsIgnoreCase(autowiredBeanName)) {
        autowiredBeanName = field.getType().getName();
      }
      // 强制访问(强吻)
      field.setAccessible(true);
      if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
        continue;
      }
      // 为什么会为null
      if (instance == null) {
        continue;
      }
      try {
        field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
    // 1. 拿到要实例化的对象类名
    String className = beanDefinition.getBeanClassName();
    // 2. 反射实例, 得到一个对象
    Object instance = null;
    try {
      if (this.singletonMap.containsKey(className)) {
        instance = this.singletonMap.get(className);
      } else {
        Class<?> clazz = Class.forName(className);
        instance = clazz.newInstance();
        this.singletonMap.put(className, instance);
        this.singletonMap.put(beanDefinition.getFactoryBeanName(), instance);
      }
    } catch (Exception e) {
      // e.printStackTrace();
    }
    return instance;
  }

  public String[] getBeanDefinitionNames() {
    return super.beanDefinitionMap.keySet().toArray(new String[0]);
  }

  public int getBeanDefinitionCount() {
    return super.beanDefinitionMap.size();
  }

  public Properties getConfig() {
    return this.reader.getConfig();
  }
}
