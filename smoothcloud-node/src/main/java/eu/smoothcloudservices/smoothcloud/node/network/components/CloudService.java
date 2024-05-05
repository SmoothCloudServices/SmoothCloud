package eu.smoothcloudservices.smoothcloud.node.network.components;

import eu.smoothcloudservices.smoothcloud.node.util.service.ServiceGroupType;
import eu.smoothcloudservices.smoothcloud.node.util.service.CloudServiceMeta;
import eu.smoothcloudservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothcloudservices.smoothcloud.node.util.service.info.ServiceInfo;
import eu.smoothcloudservices.smoothcloud.wrapper.SmoothCloudWrapper;
import io.netty.channel.Channel;

import java.util.Objects;

public class CloudService implements INetworkComponent {

    private ServiceId serviceId;
    private CloudServiceMeta meta;
    private SmoothCloudWrapper wrapper;
    private ServiceGroupType serviceGroupType;
    private ServiceInfo serviceInfo;
    private ServiceInfo lastServiceInfo;
    private Channel channel;

    public CloudService(SmoothCloudWrapper wrapper, ServiceInfo serviceInfo, CloudServiceMeta cloudServiceMeta) {
        this.serviceInfo = serviceInfo;
        this.serviceId = cloudServiceMeta.getServiceId();
        this.lastServiceInfo = serviceInfo;
        this.meta = cloudServiceMeta;
        this.wrapper = wrapper;
        this.serviceGroupType = cloudServiceMeta.getServiceGroupType();
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
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

    public CloudServiceMeta getMeta() {
        return meta;
    }

    public ServiceGroupType getServiceGroupType() {
        return serviceGroupType;
    }

    public ServiceInfo getLastServiceInfo() {
        return lastServiceInfo;
    }

    public void setLastServiceInfo(ServiceInfo lastServiceInfo) {
        this.lastServiceInfo = lastServiceInfo;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    @Override
    public String getName() {
        return getServerId();
    }

    @Override
    public SmoothCloudWrapper getWrapper() {
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
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
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
        if (!(o instanceof CloudService)) {
            return false;
        }
        final CloudService that = (CloudService) o;
        return Objects.equals(serviceId, that.serviceId) && Objects.equals(meta, that.meta) && Objects.equals(wrapper,
                that.wrapper) && serviceGroupType == that.serviceGroupType && Objects
                .equals(serviceInfo, that.serviceInfo) && Objects.equals(lastServiceInfo, that.lastServiceInfo) && Objects.equals(channel,
                that.channel);
    }
}
