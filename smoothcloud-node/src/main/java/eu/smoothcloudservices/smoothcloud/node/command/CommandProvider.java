package eu.smoothcloudservices.smoothcloud.node.command;

import eu.smoothcloudservices.smoothcloud.node.command.impl.ShutdownCommand;
import eu.smoothcloudservices.smoothcloud.node.command.impl.group.CreateGroupCommand;
import eu.smoothcloudservices.smoothcloud.node.command.impl.group.DeleteGroupCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        registerCommand("shutdown", new ShutdownCommand());
        registerCommand("creategroup", new CreateGroupCommand());
        registerCommand("deletegroup", new DeleteGroupCommand());
    }

    public void registerCommand(String id, Command command) {
        commands.put(id, command);
    }

    public void call(String[] args) {
        var arguments = new ArrayList<>(Arrays.stream(args).toList());
        var id = arguments.removeFirst();

        if (commands.containsKey(id)) {
            commands.get(id).execute(arguments.toArray(new String[0]));
        }
    }
}
