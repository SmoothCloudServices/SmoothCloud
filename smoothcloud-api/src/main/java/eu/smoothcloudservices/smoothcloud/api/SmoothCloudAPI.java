package eu.smoothcloudservices.smoothcloud.api;

import eu.smoothcloudservices.smoothcloud.api.group.ICloudGroupProvider;
import eu.smoothcloudservices.smoothcloud.api.player.ICloudPlayerProvider;
import eu.smoothcloudservices.smoothcloud.api.service.ICloudServiceProvider;
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
