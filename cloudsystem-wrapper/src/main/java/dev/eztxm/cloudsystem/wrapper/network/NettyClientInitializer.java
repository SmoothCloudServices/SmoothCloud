package dev.eztxm.cloudsystem.wrapper.network;

import dev.eztxm.cloudsystem.api.network.codec.PacketDecoder;
import dev.eztxm.cloudsystem.api.network.codec.PacketEncoder;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelInitializer;

public final class NettyClientInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast("decoder", new PacketDecoder())
                .addLast("encoder", new PacketEncoder())
                .addLast("handler", new NettyNetworkClientHandler());
    }
}
