package eu.smoothservices.smoothcloud.node.network.components;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out.PacketOutCustomSubChannelMessage;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;
import eu.smoothservices.smoothcloud.node.util.service.ServiceGroupMode;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothservices.smoothcloud.node.util.service.ServiceProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinecraftServer implements INetworkComponent {

    private ServiceId serviceId;
    private ServiceProcessMeta processMeta;
    private Wrapper wrapper;
    private ServiceGroupMode serviceGroupMode;

    private long channelLostTime = 0L;

    private ServiceInfo serviceInfo;
    private ServiceInfo lastServiceInfo;
    private Channel channel;

    public MinecraftServer(ServiceProcessMeta processMeta, Wrapper wrapper, ServiceGroupMode serviceGroupMode, ServiceInfo serviceInfo) {
        this.processMeta = processMeta;
        this.serviceId = serviceInfo.getServiceId();
        this.wrapper = wrapper;
        this.serviceGroupMode = serviceGroupMode;

        this.serviceInfo = serviceInfo;
        this.lastServiceInfo = serviceInfo;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServerInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public ServiceGroupMode getGroupMode() {
        return serviceGroupMode;
    }

    public ServiceProcessMeta getProcessMeta() {
        return processMeta;
    }

    public void sendCustomMessage(String channel, String message, Document value) {
        this.sendPacket(new PacketOutCustomSubChannelMessage(DefaultType.BUKKIT, channel, message, value));
    }

    @Override
    public Wrapper getWrapper() {
        return wrapper;
    }

    @Override
    public String getServerId() {
        return serviceId.getServerId();
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
        return getServerId();
    }

    public void disconnect() {
        if(this.channel != null) {
            this.channel.close().syncUninterruptibly();
        }
    }

    public ICloudGroup getGroup() {
        return SmoothCloudNode.getGroup(serviceId);
    }
}
