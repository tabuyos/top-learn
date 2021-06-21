/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author tabuyos
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {

  Class<?> value();

  String version() default "";
}
