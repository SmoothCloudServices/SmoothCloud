package eu.smoothcloudservices.smoothcloud.node.network.components;

import eu.smoothcloudservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothcloudservices.smoothcloud.api.group.ServerType;
import eu.smoothcloudservices.smoothcloud.node.util.service.ServiceGroupMode;
import eu.smoothcloudservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothcloudservices.smoothcloud.node.util.service.ServiceProcessMeta;
import eu.smoothcloudservices.smoothcloud.node.util.service.info.ServiceInfo;
import eu.smoothcloudservices.smoothcloud.wrapper.SmoothCloudWrapper;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinecraftServer implements INetworkComponent {

    private ServiceId serviceId;
    private ServiceProcessMeta processMeta;
    private SmoothCloudWrapper wrapper;
    private ServiceGroupMode serviceGroupMode;

    private long channelLostTime = 0L;

    private ServiceInfo serviceInfo;
    private ServiceInfo lastServiceInfo;
    private Channel channel;

    public MinecraftServer(ServiceProcessMeta processMeta, SmoothCloudWrapper wrapper, ServiceGroupMode serviceGroupMode, ServiceInfo serviceInfo) {
        this.processMeta = processMeta;
        this.serviceId = serviceInfo.getServiceId();
        this.wrapper = wrapper;
        this.serviceGroupMode = serviceGroupMode;

        this.serviceInfo = serviceInfo;
        this.lastServiceInfo = serviceInfo;
    }

    @Override
    public SmoothCloudWrapper getWrapper() {
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

    public ICloudGroup getGroup() {
        // TODO: implement this Method
        return null;
    }
}
