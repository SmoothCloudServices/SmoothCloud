package dev.eztxm.cloudsystem.wrapper.network;

import dev.eztxm.cloudsystem.api.network.Packet;
import dev.eztxm.cloudsystem.api.network.impl.AuthPacket;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.SimpleChannelInboundHandler;

public final class NettyNetworkClientHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Packet msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(new AuthPacket());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }
}
