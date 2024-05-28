package eu.smoothservices.smoothcloud.node.network;

import eu.smoothservices.smoothcloud.node.network.components.INetworkComponent;
import eu.smoothservices.smoothcloud.node.network.components.MinecraftServer;
import eu.smoothservices.smoothcloud.node.network.components.Wrapper;
import eu.smoothservices.smoothcloud.node.network.lib.SmoothNetwork;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.out.PacketOutSmoothNetwork;
import eu.smoothservices.smoothcloud.node.util.NetworkUtils;
import io.netty.channel.Channel;
import io.netty.channel.SimpleChannelInboundHandler;

public class SmoothCloudClient extends SimpleChannelInboundHandler {

    private Channel channel;
    private INetworkComponent component;

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

        if(component instanceof MinecraftServer) {
            ((MinecraftServer) component).setChannelLostTime(0L);
            component.getWrapper().sendPacket();
        }
        if(component instanceof ProxyServer) {

        }


    }

}
