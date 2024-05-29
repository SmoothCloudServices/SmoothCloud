package eu.smoothservices.smoothcloud.client.components;

import eu.smoothservices.smoothcloud.client.lib.info.NetworkInfo;
import eu.smoothservices.smoothcloud.client.network.protocol.packet.out.PacketOutCustomChannelMessage;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProxyServer implements INetworkComponent {

    private ServiceId serviceId;
    private Wrapper wrapper;

    private long channelLostTime = 0L;

    private Channel channel;
    private ProxyInfo proxyInfo;
    private ProxyInfo lastProxyInfo;
    private ProxyProcessMeta processMeta;
    private NetworkInfo networkInfo;

    public ProxyServer(ProxyProcessMeta processMeta, Wrapper wrapper, ProxyInfo proxyInfo) {
        this.processMeta = processMeta;
        this.wrapper = wrapper;
        this.serviceId = proxyInfo.getServiceId();

        this.networkInfo = new NetworkInfo(proxyInfo.getServiceId().getServerId(), proxyInfo.getHost(), proxyInfo.getPort());

        this.proxyInfo = proxyInfo;
        this.lastProxyInfo = proxyInfo;
    }

    public void sendCustomMessage(String channel, String message, Document value) {
        this.sendPacket(new PacketOutCustomChannelMessage(channel, message, value));
    }

    public void disconnect() {
        if (this.channel != null) {
            this.channel.close().syncUninterruptibly();
        }
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
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
