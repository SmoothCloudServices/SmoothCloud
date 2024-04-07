package dev.eztxm.cloudsystem.api;

import dev.eztxm.cloudsystem.api.group.CloudGroupProvider;
import dev.eztxm.cloudsystem.api.service.CloudServiceProvider;
import lombok.Getter;

public abstract class CloudSystemAPI {
    @Getter
    private static CloudSystemAPI instance;

    public CloudSystemAPI() {
        instance = this;
    }

    public abstract CloudGroupProvider getGroupProvider();
    public abstract CloudServiceProvider getServiceProvider();
}
