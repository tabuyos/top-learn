/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.framework;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tabuyos get some annotation
 *
 * @author tabuyos
 */
@SuppressWarnings({"SameParameterValue", "unused"})
@Service
@Repository
public class Tabuyos {

  public static <A extends Annotation> A getAnnotation(Object source,
                                                       Class<A> annotationClass) {
    return getAnnotation(source,
        annotationClass,
        false);
  }

  public static <A extends Annotation> A getAnnotation(Object source,
                                                       Class<A> annotationClass,
                                                       boolean inherited) {
    return getAnnotation(source.getClass(),
        annotationClass,
        inherited);
  }

  public static <A extends Annotation> A getAnnotation(Class<?> sourceClass,
                                                       Class<A> annotationClass) {
    return getAnnotation(sourceClass,
        annotationClass,
        false);
  }

  public static <A extends Annotation> A getAnnotation(Class<?> sourceClass,
                                                       Class<A> annotationClass,
                                                       boolean inherited) {
    if (inherited) {
      System.out.println("Non-support ...");
    }
    A declaredAnnotation = sourceClass.getDeclaredAnnotation(annotationClass);
    if (declaredAnnotation != null) {
      return declaredAnnotation;
    }
    List<? extends Class<? extends Annotation>> metaAnnotations = new ArrayList<>(
        Arrays.asList(Documented.class, Retention.class, Target.class));
    List<? extends Class<? extends Annotation>> collect = Arrays
        .stream(sourceClass.getDeclaredAnnotations())
        .map(Annotation::annotationType)
        .filter(el -> !metaAnnotations.contains(el))
        .collect(Collectors.toList());
    for (Class<? extends Annotation> pac : collect) {
      A annotation = getAnnotation(pac, annotationClass, inherited);
      if (annotation != null) {
        return annotation;
      }
    }
    return null;
  }
}
