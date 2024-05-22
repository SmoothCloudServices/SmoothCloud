package eu.smoothservices.smoothcloud.node.util.service.plugin;

import lombok.Getter;

@Getter
public class ServiceInstallablePlugin {

    private String name;
    private PluginResourceType pluginResourceType;
    private String url;

    public ServiceInstallablePlugin(String name, PluginResourceType pluginResourceType, String url) {
        this.name = name;
        this.pluginResourceType = pluginResourceType;
        this.url = url;
    }
}
