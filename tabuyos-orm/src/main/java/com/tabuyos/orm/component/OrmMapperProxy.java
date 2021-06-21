/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.orm.component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author tabuyos
 */
public class OrmMapperProxy implements InvocationHandler {

  private final OrmSqlSession session;

  public OrmMapperProxy(OrmSqlSession session) {
    this.session = session;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String statementId = method.getDeclaringClass().getName() + "." + method.getName();
    return session.selectOne(statementId, args[0]);
  }
}
