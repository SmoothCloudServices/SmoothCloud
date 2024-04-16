package eu.smoothcloudservices.smoothcloud.node.command.impl.group;

import eu.smoothcloudservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothcloudservices.smoothcloud.node.SmoothSmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.command.Command;
import eu.smoothcloudservices.smoothcloud.node.terminal.Color;

public final class DeleteGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        var terminal = ((SmoothSmoothCloudNode) SmoothSmoothCloudNode.getInstance()).getTerminal();

        if (args.length == 2 && args[0].equalsIgnoreCase("deletegroup")) {
            var name = args[1];

            if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
                terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 not found."));
                return;
            }
            SmoothCloudAPI.getInstance().getGroupProvider().deleteGroup(SmoothCloudAPI.getInstance().getGroupProvider().getGroup(name));
            terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 deleted."));
            return;
        }
    }
}
