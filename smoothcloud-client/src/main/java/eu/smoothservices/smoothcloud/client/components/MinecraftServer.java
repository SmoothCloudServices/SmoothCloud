package eu.smoothservices.smoothcloud.client.components;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
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
        // TODO: implement this Method
        return null;
    }
}
