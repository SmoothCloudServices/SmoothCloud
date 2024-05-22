package eu.smoothservices.smoothcloud.node.command.impl.group;

import eu.smoothservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.command.Command;

public final class DeleteGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        var terminal = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();

        if (args.length == 2 && args[0].equalsIgnoreCase("deletegroup")) {
            var name = args[1];

            if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
                terminal.append(STR."&1Group &0\{name}&1 not found.");
                return;
            }
            SmoothCloudAPI.getInstance().getGroupProvider().deleteGroup(SmoothCloudAPI.getInstance().getGroupProvider().getGroup(name));
            terminal.append(STR."&1Group &0\{name}&1 deleted.");
            return;
        }
    }
}
