package eu.smoothcloudservices.smoothcloud.node.util.service.plugin;

public class ServiceInstallablePlugin {

    private String name;
    private PluginResourceType pluginResourceType;
    private String url;

    public ServiceInstallablePlugin(String name, PluginResourceType pluginResourceType, String url) {
        this.name = name;
        this.pluginResourceType = pluginResourceType;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public PluginResourceType getPluginResourceType() {
        return pluginResourceType;
    }
}
