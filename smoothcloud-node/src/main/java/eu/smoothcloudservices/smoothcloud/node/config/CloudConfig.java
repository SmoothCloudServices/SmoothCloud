package eu.smoothcloudservices.smoothcloud.node.config;

import dev.eztxm.cloudsystem.node.config.entity.*;
import eu.smoothcloudservices.smoothcloud.node.config.entity.EulaAgreement;
import eu.smoothcloudservices.smoothcloud.node.config.entity.HostAddress;
import eu.smoothcloudservices.smoothcloud.node.config.entity.Language;

import java.io.File;

public class CloudConfig {

    private File file;
    private HostAddress nodeHost;
    private HostAddress wrapperHost;
    private EulaAgreement eulaAgreement;
    private Language language;

    public CloudConfig(String path) {
        this.file = new File(path, "config.cloud");
    }

}
