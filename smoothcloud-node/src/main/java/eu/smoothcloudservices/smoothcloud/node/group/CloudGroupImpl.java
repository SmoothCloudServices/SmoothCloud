package eu.smoothcloudservices.smoothcloud.node.group;

import eu.smoothcloudservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothcloudservices.smoothcloud.api.group.ServerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CloudGroupImpl implements ICloudGroup {

    private String name;
    private int minOnlineCount;
    private int maxOnlineCount;
    private int memory;
    private ServerType type;

}
