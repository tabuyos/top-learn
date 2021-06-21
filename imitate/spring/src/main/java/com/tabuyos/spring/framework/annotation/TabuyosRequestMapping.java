/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author tabuyos
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TabuyosRequestMapping {
  String value() default "";
}
