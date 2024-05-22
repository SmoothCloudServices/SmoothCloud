package eu.smoothservices.smoothcloud.api;

import eu.smoothservices.smoothcloud.api.group.ICloudGroupProvider;
import eu.smoothservices.smoothcloud.api.player.ICloudPlayerProvider;
import eu.smoothservices.smoothcloud.api.service.ICloudServiceProvider;
import lombok.Getter;

public abstract class SmoothCloudAPI {
    @Getter
    private static SmoothCloudAPI instance;

    public SmoothCloudAPI() {
        instance = this;
    }

    public abstract ICloudGroupProvider getGroupProvider();
    public abstract ICloudServiceProvider getServiceProvider();
    public abstract ICloudPlayerProvider getPlayerProvider();
}
