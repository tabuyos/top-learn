/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.mybatis.component;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO
 *
 * @author tabuyos
 */
public class User implements Serializable {

  private static final long serialVersionUID = 7521900555574728821L;
  private Integer id;
  private String name;
  private Integer age;
  private Integer gender;
  private Timestamp birthday;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", gender=" + gender +
        ", birthday=" + birthday +
        '}';
  }
}
