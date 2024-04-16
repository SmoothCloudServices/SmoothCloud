package eu.smoothcloudservices.smoothcloud.node.config.parse;

import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;

public class Config {

    private CloudConfig cloud;

    public CloudConfig getCloudConfig() {
        return cloud;
    }

    public void setCloudConfig(CloudConfig config) {
        this.cloud = config;
    }

    @Override
    public String toString() {
        return "Config{" +
                "cloud=" + cloud
                + "}";
    }

}
