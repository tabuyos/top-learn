/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.orm.component;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author tabuyos
 */
@SuppressWarnings("unchecked")
public class OrmConfiguration {

  public final static ResourceBundle bundle;

  static {
    bundle = ResourceBundle.getBundle("orm");
  }

  public <T> T getMapper(Class<T> clazz, OrmSqlSession session) {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new OrmMapperProxy(session));
  }
}
