/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.service.impl;

import com.tabuyos.spring.framework.annotation.TabuyosService;
import com.tabuyos.spring.service.IModifyService;

/**
 * TODO
 *
 * @author tabuyos
 */
@TabuyosService
public class ModifyServiceImpl implements IModifyService {
  @Override
  public String add(String name, String addr) {
    return "modifyService add, name: " + name + ", addr: " + addr;
  }

  @Override
  public String edit(Integer id, String name) {
    return "modifyService edit, name: " + name + ", id: " + id;
  }

  @Override
  public String remove(Integer id) {
    return "modifyService remove, id: " + id;
  }
}
