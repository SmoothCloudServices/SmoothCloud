package eu.smoothservices.smoothcloud.node.util.service;

import eu.smoothservices.smoothcloud.node.config.service.ServiceConfig;
import eu.smoothservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;
import eu.smoothservices.smoothcloud.node.util.service.template.Template;
import lombok.Getter;

import java.util.Collection;
import java.util.Properties;

@Getter
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

}
