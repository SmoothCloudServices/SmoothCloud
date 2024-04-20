package eu.smoothcloudservices.smoothcloud.node.command.impl.group;

import eu.smoothcloudservices.smoothcloud.node.command.Command;
import eu.smoothcloudservices.smoothcloud.node.setup.SetupGroup;

public final class CreateGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("creategroup")) {
            var name = args[1];
            new SetupGroup(name).setup();
        }
    }
}
