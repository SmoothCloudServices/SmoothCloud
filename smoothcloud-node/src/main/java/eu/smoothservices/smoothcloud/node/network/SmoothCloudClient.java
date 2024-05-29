package eu.smoothservices.smoothcloud.node.network;

import eu.smoothservices.smoothcloud.node.network.components.INetworkComponent;
import eu.smoothservices.smoothcloud.node.network.components.MinecraftServer;
import eu.smoothservices.smoothcloud.node.network.components.ProxyServer;
import eu.smoothservices.smoothcloud.node.network.components.Wrapper;
import eu.smoothservices.smoothcloud.node.network.lib.SmoothNetwork;
import eu.smoothservices.smoothcloud.node.network.network.NetworkUtils;
import eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out.PacketOutSmoothNetwork;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SmoothCloudClient extends SimpleChannelInboundHandler {
    private final Channel channel;
    private final INetworkComponent component;

    public SmoothCloudClient(INetworkComponent iNetworkComponent, Channel channel) {
        this.component = iNetworkComponent;
        this.component.setChannel(channel);
        this.channel = channel;

        // Service Connected
        System.out.println();

        if(iNetworkComponent instanceof Wrapper) {
            // Wrapper Connected
            System.out.println();
            // Event Call

            // Wrapper Initialization
        }

        SmoothNetwork network = NetworkUtils.newSmoothNetwork();
        channel.writeAndFlush(new PacketOutSmoothNetwork(network));

        if (component instanceof MinecraftServer) {
            ((MinecraftServer) component).setChannelLostTime(0L);
            component.getWrapper().sendPacket();
        }
        if (component instanceof ProxyServer) {

        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {

    }
}
