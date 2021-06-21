/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.beans.support;

import com.tabuyos.spring.framework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanDefinitionReader {

  private final List<String> registryBeanClasses = new ArrayList<>();
  private final Properties config = new Properties();
  // 扫描路径
  private final String SCAN_PACKAGE = "scanPackage";

  public BeanDefinitionReader(String... locations) {
    // 使用 URL 定位文件
    try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""))) {
      config.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    doScanner(config.getProperty(SCAN_PACKAGE));
  }

  private void doScanner(String property) {
    // 转换为文件路径
    URL url = this.getClass().getClassLoader().getResource(property.replaceAll("\\.", "/"));
    assert url != null;
    File classPath = new File(url.getFile());
    for (File file : Objects.requireNonNull(classPath.listFiles())) {
      if (file.isDirectory()) {
        doScanner(property + "." + file.getName());
      } else {
        if (!file.getName().endsWith(".class")) {
          continue;
        }
        String className = property + "." + file.getName().replace(".class", "");
        registryBeanClasses.add(className);
      }
    }
  }

  public Properties getConfig() {
    return this.config;
  }

  /**
   * 把配置文件中扫描到的所有的配置信息转换为 BeanDefinition  对象, 以便之后的IOC操作
   * @return beanDefinition 列表
   */
  public List<BeanDefinition> loadBeanDefinitions() {
    List<BeanDefinition> result = new ArrayList<>();
    registryBeanClasses.forEach(className -> {
      try {
        Class<?> beanClass = Class.forName(className);
        // 不是接口才能实例化
        if (!beanClass.isInterface()) {
          // beanName 有三种情况:
          // 1. 默认是类名首字母小写
          // 2. 自定义名字
          // 3. 接口注入
          result.add(doCreateBeanDefinition(uncapitalize(beanClass.getSimpleName()), beanClass.getName()));
          Class<?>[] interfaces = beanClass.getInterfaces();
          Arrays.stream(interfaces).forEach(parentBeanClass -> {
            // 如果是多个实现类, 只能覆盖
            // spring 也没有那么的智能
            // 因此此时可以自定义名字
            result.add(doCreateBeanDefinition(parentBeanClass.getName(), beanClass.getName()));
          });
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    });
    return result;
  }

  /**
   * 把每一个配置信息解析成 BeanDefinition
   * @param factoryBeanName parent name
   * @param beanClassName class name
   * @return BeanDefinition
   */
  private BeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
    BeanDefinition beanDefinition = new BeanDefinition();
    beanDefinition.setBeanClassName(beanClassName);
    beanDefinition.setFactoryBeanName(factoryBeanName);
    return beanDefinition;
  }

  private static String uncapitalize(String str) {
    return changeFirstCharacterCase(str, false);
  }

  private static String changeFirstCharacterCase(String str, boolean capitalize) {
    if (!hasLength(str)) {
      return str;
    }
    char baseChar = str.charAt(0);
    char updatedChar;
    if (capitalize) {
      updatedChar = Character.toUpperCase(baseChar);
    } else {
      updatedChar = Character.toLowerCase(baseChar);
    }
    if (baseChar == updatedChar) {
      return str;
    }

    char[] chars = str.toCharArray();
    chars[0] = updatedChar;
    return new String(chars, 0, chars.length);
  }

  private static boolean hasLength(String str) {
    return (str != null && !str.isEmpty());
  }
}
