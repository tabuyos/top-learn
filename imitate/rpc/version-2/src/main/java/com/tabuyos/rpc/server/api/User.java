/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.api;

import java.io.Serializable;

/**
 * User POJO
 *
 * @author tabuyos
 */
public class User implements Serializable {

  private static final long serialVersionUID = -5018636826499153768L;
  private Integer id;
  private Integer age;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", age=" + age +
        ", name='" + name + '\'' +
        '}';
  }
}
