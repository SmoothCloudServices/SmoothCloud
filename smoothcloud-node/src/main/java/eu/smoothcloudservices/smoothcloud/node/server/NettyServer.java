package eu.smoothcloudservices.smoothcloud.node.server;

import eu.smoothcloudservices.smoothcloud.api.misc.NettyUtils;
import io.netty5.bootstrap.ServerBootstrap;
import io.netty5.channel.EventLoopGroup;
import io.netty5.channel.MultithreadEventLoopGroup;
import io.netty5.util.concurrent.Future;

public final class NettyServer {
    private final EventLoopGroup bossGroup = new MultithreadEventLoopGroup(NettyUtils.getFactory());
    private final EventLoopGroup workGroup = new MultithreadEventLoopGroup(NettyUtils.getFactory());

    private Future<Void> future;

    public NettyServer() {
        new ServerBootstrap()
                .channelFactory(NettyUtils.getChannelFactory())
                .group(bossGroup, workGroup)
                .childHandler(new NettyNetworkServerInitializer())
                .bind("0.0.0.0", 8850)
                .addListener(futures -> {

                });
    }

    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
