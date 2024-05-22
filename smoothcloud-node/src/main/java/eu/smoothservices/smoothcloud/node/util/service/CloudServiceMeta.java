package eu.smoothservices.smoothcloud.node.util.service;

import eu.smoothservices.smoothcloud.node.config.service.ServiceConfig;
import eu.smoothservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;
import eu.smoothservices.smoothcloud.node.util.service.template.Template;
import eu.smoothservices.smoothcloud.node.util.service.template.TemplateResource;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@Getter
public class CloudServiceMeta {

    private ServiceId serviceId ;
    private int memory;
    private boolean priorityStop;
    private String[] processParameters;
    private Collection<ServiceInstallablePlugin> plugins;
    private ServiceConfig serviceConfig;
    private int port;
    private String templateName;
    private Properties serverProperties;
    private ServiceGroupType serviceGroupType;
    private Template template;

    public CloudServiceMeta(ServiceId serviceId, int memory, boolean priorityStop, String[] processParameters, Collection<ServiceInstallablePlugin> plugins, ServiceConfig serviceConfig, int port, String templateName, Properties properties, ServiceGroupType serviceGroupType) {
        this.serviceId = serviceId;
        this.memory = memory;
        this.priorityStop = priorityStop;
        this.processParameters = processParameters;
        this.plugins = plugins;
        this.serviceConfig = serviceConfig;
        this.port = port;
        this.templateName = templateName;
        this.serverProperties = properties;
        this.serviceGroupType = serviceGroupType;
        this.template = new Template(templateName, TemplateResource.MASTER, null, new String[0], new ArrayList<>());
    }
}
