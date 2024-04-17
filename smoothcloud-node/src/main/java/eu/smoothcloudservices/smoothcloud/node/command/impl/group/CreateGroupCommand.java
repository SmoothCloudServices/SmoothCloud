package eu.smoothcloudservices.smoothcloud.node.command.impl.group;

import eu.smoothcloudservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothcloudservices.smoothcloud.api.group.GroupType;
import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.command.Command;
import eu.smoothcloudservices.smoothcloud.node.group.CloudGroupImpl;
import eu.smoothcloudservices.smoothcloud.node.terminal.Color;

public final class CreateGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        var terminal = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();

        if (args.length == 3 && args[0].equalsIgnoreCase("creategroup")) {
            var name = args[1];
            var type = args[2];

            if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
                SmoothCloudAPI.getInstance().getGroupProvider().createGroup(new CloudGroupImpl(name, 1, 1, GroupType.valueOf(type.toUpperCase())));
                terminal.append(STR."&1Group &0\{name}&1 created.");
                return;
            }
            terminal.append(STR."&1Group &0\{name}&1 already exists.");
        }
    }
}
