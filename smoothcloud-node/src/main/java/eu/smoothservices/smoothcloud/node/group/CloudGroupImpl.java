package eu.smoothservices.smoothcloud.node.group;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.api.group.ServerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CloudGroupImpl implements ICloudGroup {

    private String name;
    private String templateName;
    private int minMemory;
    private int maxMemory;
    private int maxPlayers;
    private int minOnlineCount;
    private int maxOnlineCount;
    private String permission;
    private boolean maintenance;
    private boolean staticService;
    private int priority;
    private String wrapperName;
    private int percentOfPlayersOnGroupToStartNewService;
    private ServerType type;
    private String version;
    private int javaVersion;


}
