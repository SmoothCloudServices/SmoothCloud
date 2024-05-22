package eu.smoothservices.smoothcloud.node.command.impl.group;

import eu.smoothservices.smoothcloud.node.command.Command;
import eu.smoothservices.smoothcloud.node.setup.SetupGroup;

public final class CreateGroupCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("creategroup")) {
            var name = args[1];
            new SetupGroup(name).setup();
        }
    }
}
