package dev.eztxm.cloudsystem.node.command.impl.group;

import dev.eztxm.cloudsystem.api.CloudSystemAPI;
import dev.eztxm.cloudsystem.api.group.GroupType;
import dev.eztxm.cloudsystem.node.CloudSystemNode;
import dev.eztxm.cloudsystem.node.command.Command;
import dev.eztxm.cloudsystem.node.group.CloudGroupImpl;
import dev.eztxm.cloudsystem.node.terminal.Color;

public final class CreateGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        var terminal = ((CloudSystemNode) CloudSystemNode.getInstance()).getTerminal();

        if (args.length == 3 && args[0].equalsIgnoreCase("creategroup")) {
            var name = args[1];
            var type = args[2];

            if (!CloudSystemAPI.getInstance().getGroupProvider().existsGroup(name)) {
                CloudSystemAPI.getInstance().getGroupProvider().createGroup(new CloudGroupImpl(name, 1, 1, GroupType.valueOf(type.toUpperCase())));
                terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 created."));
                return;
            }
            terminal.write(Color.translate(STR."&0CloudSystem &2» &1Group &0\{name}&1 already exists."));
        }
    }
}
