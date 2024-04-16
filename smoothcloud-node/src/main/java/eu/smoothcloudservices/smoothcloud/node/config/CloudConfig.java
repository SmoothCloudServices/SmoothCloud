package eu.smoothcloudservices.smoothcloud.node.config;

import eu.smoothcloudservices.smoothcloud.node.config.entity.*;

import java.io.File;

public class CloudConfig {

    private final File file;
    private HostAddress nodeHost;
    private HostAddress wrapperHost;
    private EulaAgreement eulaAgreement;
    private Language language;

    public CloudConfig() {
        this.file = new File("config.cfg");
    }
}
