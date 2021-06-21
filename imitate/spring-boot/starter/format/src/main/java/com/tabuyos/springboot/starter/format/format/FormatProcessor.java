/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.format.format;

/**
 * TODO
 *
 * @author tabuyos
 */
public interface FormatProcessor {
  <T> String format(T object);
}
