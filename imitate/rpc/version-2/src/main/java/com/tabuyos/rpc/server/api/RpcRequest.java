/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.api;

import java.io.Serializable;
import java.util.Arrays;

/**
 * request for rpc
 *
 * @author tabuyos
 */
public class RpcRequest implements Serializable {

  private static final long serialVersionUID = -7558559211538723973L;
  private String className;
  private String methodName;
  private String version;
  private Object[] parameters;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Object[] getParameters() {
    return parameters;
  }

  public void setParameters(Object[] parameters) {
    this.parameters = parameters;
  }

  @Override
  public String toString() {
    return "RpcRequest{" +
        "className='" + className + '\'' +
        ", methodName='" + methodName + '\'' +
        ", version='" + version + '\'' +
        ", parameters=" + Arrays.toString(parameters) +
        '}';
  }
}
