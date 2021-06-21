/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.format;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 *
 * @author tabuyos
 */
public class JsonFormatProcessor implements FormatProcessor {
  @Override
  public <T> String format(T object) {
    return "JsonFormatProcessor: " + JSON.toJSONString(object);
  }
}
