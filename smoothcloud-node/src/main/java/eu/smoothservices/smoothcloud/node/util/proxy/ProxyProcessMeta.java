package eu.smoothservices.smoothcloud.node.util.proxy;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;

import java.util.Collection;

public class ProxyProcessMeta {

    private ServiceId serviceId;

    private int memory;

    private int port;

    private String[] processParameters;

    private String url;

    private Collection<ServiceInstallablePlugin> downloadablePlugins;

    private Document properties;

    private ICloudGroup group;

    public ProxyProcessMeta(ServiceId serviceId,
                            int memory,
                            int port,
                            String[] processParameters,
                            String url,
                            Collection<ServiceInstallablePlugin> downloadablePlugins,
                            Document properties) {
        this.serviceId = serviceId;
        this.memory = memory;
        this.port = port;
        this.processParameters = processParameters;
        this.url = url;
        this.downloadablePlugins = downloadablePlugins;
        this.properties = properties;
        this.group = SmoothCloudNode.getGroup(serviceId);
    }

    public int getMemory() {
        return memory;
    }

    public Document getProperties() {
        return properties;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public int getPort() {
        return port;
    }

    public Collection<ServiceInstallablePlugin> getDownloadablePlugins() {
        return downloadablePlugins;
    }

    public String getUrl() {
        return url;
    }

    public String[] getProcessParameters() {
        return processParameters;
    }

    public ICloudGroup getGroup() {
        return group;
    }
}
