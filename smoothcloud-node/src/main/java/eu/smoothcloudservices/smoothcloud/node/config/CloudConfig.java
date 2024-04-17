package eu.smoothcloudservices.smoothcloud.node.config;

import eu.smoothcloudservices.smoothcloud.node.config.entity.EulaAgreement;
import eu.smoothcloudservices.smoothcloud.node.config.entity.HostAddress;
import eu.smoothcloudservices.smoothcloud.node.config.entity.Language;
import lombok.Getter;
import lombok.Setter;
import me.syntaxjason.SyntConf;

import java.io.File;

@Setter
@Getter
public class CloudConfig extends SyntConf {

    private HostAddress cloudHost;
    private Language language;
    private EulaAgreement eulaAgreement;

    public CloudConfig(String path, String name) {
        super(path, name);

    }

    public CloudConfig(File file) {
        super(file);
    }

}
