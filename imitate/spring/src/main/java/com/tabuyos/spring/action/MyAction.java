/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.action;

import com.tabuyos.spring.framework.annotation.TabuyosAutowired;
import com.tabuyos.spring.framework.annotation.TabuyosController;
import com.tabuyos.spring.framework.annotation.TabuyosRequestMapping;
import com.tabuyos.spring.framework.annotation.TabuyosRequestParam;
import com.tabuyos.spring.service.IModifyService;
import com.tabuyos.spring.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author tabuyos
 */
@TabuyosController
@TabuyosRequestMapping("tabuyos")
public class MyAction {

  @TabuyosAutowired
  private IQueryService queryService;
  @TabuyosAutowired
  private IModifyService modifyService;

  @TabuyosRequestMapping("querey.json")
  public void query(HttpServletRequest request,
                    HttpServletResponse response,
                    @TabuyosRequestParam("name") String name) {
    String result = queryService.query(name);
    out(response, result);
  }

  @TabuyosRequestMapping("add*.json")
  public void add(HttpServletRequest request,
                  HttpServletResponse response,
                  @TabuyosRequestParam("name") String name,
                  @TabuyosRequestParam("addr") String addr) {
    String result = modifyService.add(name, addr);
    out(response, result);
  }

  @TabuyosRequestMapping("edit.json")
  public void query(HttpServletRequest request, HttpServletResponse response,
                    @TabuyosRequestParam("id") Integer id,
                    @TabuyosRequestParam("name") String name) {
    String result = modifyService.edit(id, name);
    out(response, result);
  }

  private void out(HttpServletResponse response, String string) {
    try {
      response.getWriter().write(string);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
