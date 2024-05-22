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
    private String version;
    private String wrapperName;

    private int minOnlineCount;
    private int maxOnlineCount;
    private int minMemory;
    private int maxMemory;

    private ServerType type;

    private boolean staticService;
}
