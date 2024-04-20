package eu.smoothcloudservices.smoothcloud.api.player;

import eu.smoothcloudservices.smoothcloud.api.service.ICloudService;

import java.util.UUID;

public interface ICloudPlayer {

    ICloudService getCurrentService();
    String getCurrentServiceName();
    ICloudService getCurrentProxyService();
    String getCurrentProxyServiceName();

    UUID getUniqueId();
    String getName();
    int getPing();

}
