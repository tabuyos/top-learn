/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.mybatis.component;

import java.sql.*;

/**
 * TODO
 *
 * @author tabuyos
 */
@SuppressWarnings("unchecked")
public class OrmExecutor {
  public <T> T query(String sql, Object parameter) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    User user = new User();

    try {
      connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tabuyos", "tabuyos", "tabuyos");
      statement = connection.createStatement();
      resultSet = statement.executeQuery(String.format(sql, parameter));
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        user.setId(id);
        user.setName(name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return (T) user;
  }
}
