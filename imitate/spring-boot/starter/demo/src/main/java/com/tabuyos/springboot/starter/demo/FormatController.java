/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.springboot.starter.demo;

import com.tabuyos.springboot.starter.format.HelloFormatTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author tabuyos
 */
@RestController
public class FormatController {

  private final HelloFormatTemplate helloFormatTemplate;

  public FormatController(HelloFormatTemplate helloFormatTemplate) {
    this.helloFormatTemplate = helloFormatTemplate;
  }

  @GetMapping("format")
  public String format() {
    User user = new User();
    user.setId(1);
    user.setUsername("tabuyos");
    user.setAge(25);
    return helloFormatTemplate.doFormat(user);
  }
}
