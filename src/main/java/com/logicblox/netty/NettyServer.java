package com.simple.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Optional;

@Component
public class NettyServer
{
    @Autowired
    private ServerBootstrap serverBootstrap;
    private Optional<Channel> serverChannel;

    public void start() {
        try {
            serverChannel = Optional.of(serverBootstrap.bind(8080).sync().channel().closeFuture().sync().channel());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stop() {
        if (serverChannel.isPresent()) {
            serverChannel.get().close();
            serverChannel.get().parent().close();
        }
    }
}