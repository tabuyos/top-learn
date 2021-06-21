/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author tabuyos
 */
public class TabuyosContext {

  public static void main(String[] args) {
    System.out.println(Tabuyos.getAnnotation(Tabuyos.class, Controller.class));
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    System.out.println(list.stream().filter(el -> el == 5).findAny().orElse(0));
  }
}
