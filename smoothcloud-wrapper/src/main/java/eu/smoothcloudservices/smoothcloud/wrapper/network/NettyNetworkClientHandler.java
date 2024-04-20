package eu.smoothcloudservices.smoothcloud.wrapper.network;

import eu.smoothcloudservices.smoothcloud.api.network.IPacket;
import eu.smoothcloudservices.smoothcloud.api.network.impl.AuthPacket;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.SimpleChannelInboundHandler;

public final class NettyNetworkClientHandler extends SimpleChannelInboundHandler<IPacket> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, IPacket msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(new AuthPacket());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }
}
