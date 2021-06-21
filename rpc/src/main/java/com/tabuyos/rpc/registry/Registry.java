/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * TODO
 *
 * @author tabuyos
 */
public class Registry {

  private int port;

  public Registry(int port) {
    this.port = port;
  }

  public void start() {
    // ServerSocket /ServerSocketChannel
    // base on NIO
    // selector, worker
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            // 在netty中将所有的业务都归到一个队列
            // 这个队列包含了所有的逻辑, 这些逻辑在netty中有一个封装
            // 封装成了一个对象, 无锁化串行任务队列
            // Pipeline

            // 就是对处理逻辑的封装
            ChannelPipeline pipeline = ch.pipeline();
            // 对于自定义协议的内容要进行编码, 解码
            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
            pipeline.addLast(new LengthFieldPrepender(4));
            // 实参处理
            pipeline.addLast("encoder", new ObjectEncoder());
            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
            pipeline.addLast(new RegistryHandler());
          }
        });
  }

  public static void main(String[] args) {
    new Registry(8080).start();
  }
}
