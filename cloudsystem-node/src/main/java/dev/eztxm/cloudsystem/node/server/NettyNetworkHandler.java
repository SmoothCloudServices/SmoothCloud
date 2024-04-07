package dev.eztxm.cloudsystem.node.server;

import dev.eztxm.cloudsystem.api.network.Packet;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.SimpleChannelInboundHandler;

public final class NettyNetworkHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Packet msg) {
        System.out.println(STR."Package \{msg.getClass().getSimpleName()} received");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel has been successfully connected");
    }
}
