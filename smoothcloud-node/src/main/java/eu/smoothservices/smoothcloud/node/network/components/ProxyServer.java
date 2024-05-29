package eu.smoothservices.smoothcloud.node.network.components;

import eu.smoothservices.smoothcloud.node.network.lib.info.NetworkInfo;
import eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out.PacketOutCustomChannelMessage;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProxyServer implements INetworkComponent {
    private final ServiceId serviceId;
    private final Wrapper wrapper;
    private final ProxyProcessMeta processMeta;
    private final NetworkInfo networkInfo;
    @Setter
    private long channelLostTime = 0L;
    private Channel channel;
    @Setter
    private ProxyInfo proxyInfo;
    @Setter
    private ProxyInfo lastProxyInfo;

    public ProxyServer(ProxyProcessMeta processMeta, Wrapper wrapper, ProxyInfo proxyInfo) {
        this.serviceId = proxyInfo.getServiceId();
        this.wrapper = wrapper;
        this.processMeta = processMeta;
        this.networkInfo = new NetworkInfo(proxyInfo.getServiceId().getServerId(), proxyInfo.getHost(), proxyInfo.getPort());
        this.proxyInfo = proxyInfo;
        this.lastProxyInfo = proxyInfo;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void disconnect() {
        if (this.channel != null) {
            this.channel.close().syncUninterruptibly();
        }
    }

    public void sendCustomMessage(String channel, String message, Document value) {
        this.sendPacket(new PacketOutCustomChannelMessage(channel, message, value));
    }

    @Override
    public String getName() {
        return serviceId.getServerId();
    }

    @Override
    public Wrapper getWrapper() {
        return wrapper;
    }

    @Override
    public String getServerId() {
        return serviceId.getServerId();
    }

}
