package eu.smoothcloudservices.smoothcloud.node.server;

import eu.smoothcloudservices.smoothcloud.api.misc.NettyUtils;
import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import io.netty5.bootstrap.ServerBootstrap;
import io.netty5.channel.EventLoopGroup;
import io.netty5.channel.MultithreadEventLoopGroup;
import io.netty5.util.concurrent.Future;

public final class NettyServer {
    private final EventLoopGroup bossGroup = new MultithreadEventLoopGroup(NettyUtils.getFactory());
    private final EventLoopGroup workGroup = new MultithreadEventLoopGroup(NettyUtils.getFactory());

    private Future<Void> future;

    public NettyServer() {

        CloudConfig config = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig();

        /*new ServerBootstrap()
                .channelFactory(NettyUtils.getChannelFactory())
                .group(bossGroup, workGroup)
                .childHandler(new NettyNetworkServerInitializer())
                .bind(config.getAddress().getHostName(), Integer.parseInt(config.getAddress().getHostPort()))
                .addListener(futures -> {
                });
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().closeAppend(SmoothCloudNode.PREFIX, STR."Netty Connection successfully started on HostAddress: \{config.getAddress().getHostAddress()}");
    */
    }


    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
