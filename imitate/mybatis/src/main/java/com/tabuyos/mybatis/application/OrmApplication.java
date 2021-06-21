/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.mybatis.application;

import com.tabuyos.orm.component.OrmConfiguration;
import com.tabuyos.orm.component.OrmExecutor;
import com.tabuyos.orm.component.OrmSqlSession;
import com.tabuyos.orm.component.UserMapper;

/**
 * Start up class
 *
 * @author tabuyos
 */
public class OrmApplication {

  public static void main(String[] args) {
    OrmConfiguration configuration = new OrmConfiguration();
    OrmExecutor executor = new OrmExecutor();
    OrmSqlSession session = new OrmSqlSession(configuration, executor);
    UserMapper mapper = session.getMapper(UserMapper.class);
    System.out.println(mapper.selectUserById(1));
  }
}
