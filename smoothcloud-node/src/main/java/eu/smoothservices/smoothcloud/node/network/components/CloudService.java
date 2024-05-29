package eu.smoothservices.smoothcloud.node.network.components;

import eu.smoothservices.smoothcloud.node.util.service.ServiceGroupType;
import eu.smoothservices.smoothcloud.node.util.service.CloudServiceMeta;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class CloudService implements INetworkComponent {
    private final ServiceId serviceId;
    private final CloudServiceMeta serviceMeta;
    private final Wrapper wrapper;
    private final ServiceGroupType serviceGroupType;
    private final ServiceInfo lastServiceInfo;
    @Setter
    private ServiceInfo serviceInfo;
    private Channel channel;

    public CloudService(Wrapper wrapper, ServiceInfo serviceInfo, CloudServiceMeta cloudServiceMeta) {
        this.serviceId = cloudServiceMeta.getServiceId();
        this.serviceMeta = cloudServiceMeta;
        this.wrapper = wrapper;
        this.serviceGroupType = cloudServiceMeta.getServiceGroupType();
        this.lastServiceInfo = serviceInfo;
        this.serviceInfo = serviceInfo;
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

    @Override
    public Wrapper getWrapper() {
        return wrapper;
    }

    @Override
    public String getServerId() {
        return serviceId.getServerId();
    }

    public void disconnect() {
        if(this.channel != null) {
            this.channel.close().syncUninterruptibly();
        }
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (serviceMeta != null ? serviceMeta.hashCode() : 0);
        result = 31 * result + (wrapper != null ? wrapper.hashCode() : 0);
        result = 31 * result + (serviceGroupType != null ? serviceGroupType.hashCode() : 0);
        result = 31 * result + (serviceInfo != null ? serviceInfo.hashCode() : 0);
        result = 31 * result + (lastServiceInfo != null ? lastServiceInfo.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CloudService that)) {
            return false;
        }
        return Objects.equals(serviceId, that.serviceId) && Objects.equals(serviceMeta, that.serviceMeta) && Objects.equals(wrapper,
                that.wrapper) && serviceGroupType == that.serviceGroupType && Objects
                .equals(serviceInfo, that.serviceInfo) && Objects.equals(lastServiceInfo, that.lastServiceInfo) && Objects.equals(channel,
                that.channel);
    }
}
