/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework.beans.support;

import com.tabuyos.framework.beans.config.BeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * TODO
 *
 * @author tabuyos
 */
public class BeanDefinitionReader {

  private Properties config = new Properties();
  private final String SCAN_PACKAGES = "scanBasePackage";

  public BeanDefinitionReader(String[] configLocations) {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath", ""));
    try {
      config.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    }
    doScanner(config.getProperty(SCAN_PACKAGES));
  }

  private void doScanner(String property) {

  }

  public List<BeanDefinition> loadBeanDefinitions() {
    return null;
  }
}
