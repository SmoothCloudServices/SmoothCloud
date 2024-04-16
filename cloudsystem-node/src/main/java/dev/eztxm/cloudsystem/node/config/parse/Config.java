package dev.eztxm.cloudsystem.node.config.parse;

import dev.eztxm.cloudsystem.node.config.CloudConfig;

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
