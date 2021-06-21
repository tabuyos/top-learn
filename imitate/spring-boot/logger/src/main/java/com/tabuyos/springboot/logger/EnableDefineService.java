/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.logger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author tabuyos
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class, LoggerDefinitionRegistrar.class})
public @interface EnableDefineService {

  Class<?>[] exclude() default {};
}
