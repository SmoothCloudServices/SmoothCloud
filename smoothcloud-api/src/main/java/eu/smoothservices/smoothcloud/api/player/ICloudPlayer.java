package eu.smoothservices.smoothcloud.api.player;

import eu.smoothservices.smoothcloud.api.service.ICloudService;

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
