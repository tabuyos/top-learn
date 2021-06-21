package com.tabuyos.framework;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author tabuyos
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
  String value() default "";
}
