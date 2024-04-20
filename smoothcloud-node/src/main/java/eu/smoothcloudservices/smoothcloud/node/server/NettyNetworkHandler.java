package eu.smoothcloudservices.smoothcloud.node.server;

import eu.smoothcloudservices.smoothcloud.api.network.IPacket;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.SimpleChannelInboundHandler;

public final class NettyNetworkHandler extends SimpleChannelInboundHandler<IPacket> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, IPacket msg) {
        System.out.println(STR."Package \{msg.getClass().getSimpleName()} received");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel has been successfully connected");
    }
}
