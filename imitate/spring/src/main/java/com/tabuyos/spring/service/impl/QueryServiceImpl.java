/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.service.impl;

import com.tabuyos.spring.framework.annotation.TabuyosService;
import com.tabuyos.spring.service.IQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author tabuyos
 */
@TabuyosService
public class QueryServiceImpl implements IQueryService {

  private final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

  @Override
  public String query(String name) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = simpleDateFormat.format(new Date());
    String json = "{name: \"" + name + "\", time: \"" + time + "\"}";
    log.info(json);
    return json;
  }
}
