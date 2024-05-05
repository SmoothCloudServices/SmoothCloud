package eu.smoothcloudservices.smoothcloud.node.util.service;

import eu.smoothcloudservices.smoothcloud.node.config.service.ServiceConfig;
import eu.smoothcloudservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;
import eu.smoothcloudservices.smoothcloud.node.util.service.template.Template;

import java.util.Collection;
import java.util.Properties;

public class ServiceProcessMeta {

    private ServiceId serviceId;

    private int memory;

    private boolean priorityStop;

    private String url;

    private String[] processParameters;

    private boolean onlineMode;

    private Collection<ServiceInstallablePlugin> downloadablePlugins;

    private ServiceConfig serverConfig;

    private String customServerDownload;

    private int port;

    private Properties serverProperties;

    private Template template;

    public ServiceProcessMeta(ServiceId serviceId, int memory, boolean priorityStop, String url, String[] processParameters, boolean onlineMode, Collection<ServiceInstallablePlugin> downloadablePlugins, ServiceConfig serverConfig, String customServerDownload, int port, Properties serverProperties, Template template) {
        this.serviceId = serviceId;
        this.memory = memory;
        this.priorityStop = priorityStop;
        this.url = url;
        this.processParameters = processParameters;
        this.onlineMode = onlineMode;
        this.downloadablePlugins = downloadablePlugins;
        this.serverConfig = serverConfig;
        this.customServerDownload = customServerDownload;
        this.port = port;
        this.serverProperties = serverProperties;
        this.template = template;
    }

    public String getUrl() {
        return url;
    }

    public String[] getProcessParameters() {
        return processParameters;
    }

    public Collection<ServiceInstallablePlugin> getDownloadablePlugins() {
        return downloadablePlugins;
    }

    public int getPort() {
        return port;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public int getMemory() {
        return memory;
    }

    public Template getTemplate() {
        return template;
    }

    public ServiceConfig getServerConfig() {
        return serverConfig;
    }

    public Properties getServerProperties() {
        return serverProperties;
    }

    public String getCustomServerDownload() {
        return customServerDownload;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }

    public boolean isPriorityStop() {
        return priorityStop;
    }
}
