package dev.eztxm.cloudsystem.node.command.impl.group;

import dev.eztxm.cloudsystem.api.CloudSystemAPI;
import dev.eztxm.cloudsystem.node.CloudSystemNode;
import dev.eztxm.cloudsystem.node.command.Command;
import dev.eztxm.cloudsystem.node.terminal.Color;

public final class DeleteGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        var terminal = ((CloudSystemNode) CloudSystemNode.getInstance()).getTerminal();

        if (args.length == 2 && args[0].equalsIgnoreCase("deletegroup")) {
            var name = args[1];

            if (!CloudSystemAPI.getInstance().getGroupProvider().existsGroup(name)) {
                terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 not found."));
                return;
            }
            CloudSystemAPI.getInstance().getGroupProvider().deleteGroup(CloudSystemAPI.getInstance().getGroupProvider().getGroup(name));
            terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 deleted."));
            return;
        }
    }
}
