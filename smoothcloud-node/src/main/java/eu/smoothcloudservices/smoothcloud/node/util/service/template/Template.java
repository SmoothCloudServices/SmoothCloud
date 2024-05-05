package eu.smoothcloudservices.smoothcloud.node.util.service.template;

import eu.smoothcloudservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;

import java.util.Arrays;
import java.util.Collection;

public class Template {
    private String name;
    private TemplateResource backend;
    private String url;
    private String[] processPreParameters;
    private Collection<ServiceInstallablePlugin> installablePlugins;
    public Template(String name,
                    TemplateResource backend,
                    String url,
                    String[] processPreParameters,
                    Collection<ServiceInstallablePlugin> installablePlugins) {
        this.name = name;
        this.backend = backend;
        this.url = url;
        this.processPreParameters = processPreParameters;
        this.installablePlugins = installablePlugins;
    }

    @Override
    public String toString() {
        return "Template{" + "name='" + name + '\'' + ", backend=" + backend + ", url='" + url + '\'' + ", processPreParameters=" + Arrays.toString(
                processPreParameters) + ", installablePlugins=" + installablePlugins + '}';
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Collection<ServiceInstallablePlugin> getInstallablePlugins() {
        return installablePlugins;
    }

    public String[] getProcessPreParameters() {
        return processPreParameters;
    }

    public TemplateResource getBackend() {
        return backend;
    }
}
