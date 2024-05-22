package eu.smoothservices.smoothcloud.node.server;

import eu.smoothservices.smoothcloud.api.network.IPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public final class NettyNetworkHandler extends SimpleChannelInboundHandler<IPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPacket msg) throws Exception {
        System.out.println(STR."Package \{msg.getClass().getSimpleName()} received");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel has been successfully connected");
    }
}
