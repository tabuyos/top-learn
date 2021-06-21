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
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TabuyosRequestParam {
  String value() default "";
  boolean required() default true;
}
