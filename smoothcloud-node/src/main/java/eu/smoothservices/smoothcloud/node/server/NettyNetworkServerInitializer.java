package eu.smoothservices.smoothcloud.node.server;

import eu.smoothservices.smoothcloud.api.network.codec.PacketDecoder;
import eu.smoothservices.smoothcloud.api.network.codec.PacketEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public final class NettyNetworkServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast("decoder", new PacketDecoder())
                .addLast("encoder", new PacketEncoder())
                .addLast("network-handler", new NettyNetworkHandler());
    }
}
