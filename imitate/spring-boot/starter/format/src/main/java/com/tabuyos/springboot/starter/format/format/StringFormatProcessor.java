/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.format;

import java.util.Objects;

/**
 * TODO
 *
 * @author tabuyos
 */
public class StringFormatProcessor implements FormatProcessor{
  @Override
  public <T> String format(T object) {
    return "StringFormatProcessor: " + object.toString();
  }
}
