package dev.eztxm.cloudsystem.node.group;

import dev.eztxm.cloudsystem.api.group.CloudGroup;
import dev.eztxm.cloudsystem.api.group.GroupType;
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
