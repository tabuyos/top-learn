/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.server.provider;

import com.tabuyos.rpc.server.api.IPaymentService;

/**
 * TODO
 *
 * @author tabuyos
 */
@RpcService(IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService {

  @Override
  public void doPay() {
    System.out.println("call the doPay method");
  }
}
