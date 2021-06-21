/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.logger;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * TODO
 *
 * @author tabuyos
 */
public class LoggerDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    Class<LoggerService> beanClass = LoggerService.class;
    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(beanClass);
    String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());
    registry.registerBeanDefinition(beanName, rootBeanDefinition);
  }
}
