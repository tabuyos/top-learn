package com.tabuyos.spring.service;

/**
 * TODO
 *
 * @author tabuyos
 */
public interface IModifyService {
  String add(String name, String addr);

  String edit(Integer id, String name);

  String remove(Integer id);
}
