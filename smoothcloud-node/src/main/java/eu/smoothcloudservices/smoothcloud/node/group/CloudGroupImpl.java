package eu.smoothcloudservices.smoothcloud.node.group;

import eu.smoothcloudservices.smoothcloud.api.group.CloudGroup;
import eu.smoothcloudservices.smoothcloud.api.group.GroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CloudGroupImpl implements CloudGroup {

    private String name;
    private int minOnlineCount;
    private int maxOnlineCount;
    private GroupType type;

}
