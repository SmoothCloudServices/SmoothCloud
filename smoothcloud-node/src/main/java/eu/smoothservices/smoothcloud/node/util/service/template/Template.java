package eu.smoothservices.smoothcloud.node.util.service.template;

import eu.smoothservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;

@Getter
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
        return StringTemplate.STR."Template{name='\{name}\{'\''}, backend=\{backend}, url='\{url}\{'\''}, processPreParameters=\{Arrays.toString(
                processPreParameters)}, installablePlugins=\{installablePlugins}\{'}'}";
    }

}
