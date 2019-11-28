package com.simple.netty;

import com.simple.netty.handlers.RequestHandler;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelInitializer extends io.netty.channel.ChannelInitializer {

  @Autowired
  private RequestHandler requestHandler;

  @Override
  protected void initChannel(Channel channel) throws Exception {
    channel.pipeline()
            .addLast("codec", new HttpServerCodec())
            .addLast("aggregator", new HttpObjectAggregator(512*1024))
            .addLast("requestHandler", requestHandler);
  }
}
