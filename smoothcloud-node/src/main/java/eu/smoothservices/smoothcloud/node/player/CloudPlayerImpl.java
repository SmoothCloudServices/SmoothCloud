package eu.smoothservices.smoothcloud.node.player;

import eu.smoothservices.smoothcloud.api.player.ICloudPlayer;
import eu.smoothservices.smoothcloud.api.service.ICloudService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CloudPlayerImpl implements ICloudPlayer {

    private ICloudService currentService;
    private String currentServiceName;
    private ICloudService currentProxyService;
    private String currentProxyServiceName;
    private UUID uniqueId;
    private String name;
    private int ping;
}
