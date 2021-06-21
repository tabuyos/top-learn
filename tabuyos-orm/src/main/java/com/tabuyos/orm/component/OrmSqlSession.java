/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.orm.component;

/**
 * TODO
 *
 * @author tabuyos
 */
public class OrmSqlSession {

  private final OrmConfiguration configuration;
  private final OrmExecutor executor;

  public OrmSqlSession(OrmConfiguration configuration, OrmExecutor executor) {
    this.configuration = configuration;
    this.executor = executor;
  }

  public <T> T selectOne(String statementId, Object parameter) {
    String sql = OrmConfiguration.bundle.getString(statementId);
    return executor.query(sql, parameter);
  }

  public <T> T getMapper(Class<T> clazz) {
    return configuration.getMapper(clazz, this);
  }
}
