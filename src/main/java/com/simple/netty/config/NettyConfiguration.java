package com.simple.netty.config;

import com.simple.netty.ChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class NettyConfiguration {

  @Value("${netty.tcp.port:8080}")
  private Integer tcpPort;

  @Value("${netty.boss.thread.count:1}")
  private int bossCount;

  @Value("${netty.worker.thread.count:1}")
  private int workerCount;

  @Autowired
  ChannelInitializer channelInitializer;

  @Bean(name = "serverBootstrap")
  @Scope("singleton")
  public ServerBootstrap bootstrap() {
    ServerBootstrap b = new ServerBootstrap();
    b.group(bossGroup(), workerGroup())
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(channelInitializer);
    return b;
  }

  @Bean(name = "tcpPort")
  public Integer tcpPort() { return tcpPort; }

  @Bean(destroyMethod = "shutdownGracefully")
  public NioEventLoopGroup bossGroup() {
    return new NioEventLoopGroup(bossCount);
  }

  @Bean(destroyMethod = "shutdownGracefully")
  public NioEventLoopGroup workerGroup() {
    return new NioEventLoopGroup(workerCount);
  }
}
