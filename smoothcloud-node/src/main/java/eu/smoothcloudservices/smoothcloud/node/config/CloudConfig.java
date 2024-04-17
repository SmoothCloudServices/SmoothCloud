package eu.smoothcloudservices.smoothcloud.node.config;

import eu.smoothcloudservices.smoothcloud.node.config.entity.EulaAgreement;
import eu.smoothcloudservices.smoothcloud.node.config.entity.HostAddress;
import eu.smoothcloudservices.smoothcloud.node.config.entity.Language;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class CloudConfig {

    private File file;

    @Getter
    @Setter
    private HostAddress address;
    @Getter
    @Setter
    private EulaAgreement agreement;
    @Getter
    @Setter
    private Language language;

    public CloudConfig(File file) {
        this.file = file;
    }

    public void load() {
        JsonConfig config = new JsonConfig(file.getParent(), "config.cfg");
        this.agreement = new EulaAgreement(config.getBoolean("eulaAgreement"));
        this.address = new HostAddress(config.getString("hostName"), config.getString("hostPort"));
        this.language = new Language();
    }

    public void save() {
        JsonConfig config = new JsonConfig(file.getParent(), "config.cfg");
        config.setBoolean("eulaAgreement", agreement.getAgreement());
        config.setString("hostName", address.getHostName());
        config.setString("hostPort", address.getHostPort());
    }

}
