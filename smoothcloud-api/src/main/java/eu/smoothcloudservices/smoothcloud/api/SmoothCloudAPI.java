package eu.smoothcloudservices.smoothcloud.api;

import eu.smoothcloudservices.smoothcloud.api.group.CloudGroupProvider;
import eu.smoothcloudservices.smoothcloud.api.service.CloudServiceProvider;
import lombok.Getter;

public abstract class SmoothCloudAPI {
    @Getter
    private static SmoothCloudAPI instance;

    public SmoothCloudAPI() {
        instance = this;
    }

    public abstract CloudGroupProvider getGroupProvider();
    public abstract CloudServiceProvider getServiceProvider();
}
