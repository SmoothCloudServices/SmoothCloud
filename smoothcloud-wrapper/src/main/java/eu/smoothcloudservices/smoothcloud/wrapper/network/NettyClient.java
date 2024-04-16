package eu.smoothcloudservices.smoothcloud.wrapper.network;

import eu.smoothcloudservices.smoothcloud.api.misc.NettyUtils;
import io.netty5.bootstrap.Bootstrap;
import io.netty5.channel.ChannelOption;
import io.netty5.channel.EventLoopGroup;
import io.netty5.channel.MultithreadEventLoopGroup;

public final class NettyClient {
    private final EventLoopGroup eventLoopGroup = new MultithreadEventLoopGroup(NettyUtils.getFactory());

    public void connect(String host, int port) {
        new Bootstrap()
                .group(eventLoopGroup)
                .channelFactory(NettyUtils.getSocketChannelFactory())
                .handler(new NettyClientInitializer())
                .option(ChannelOption.TCP_NODELAY, true)
                .connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(STR."Successfully connected to \{host}:\{port}");
                    } else {
                        System.out.println(STR."Failed to connected to \{host}:\{port}");
                    }
                });
    }
}
