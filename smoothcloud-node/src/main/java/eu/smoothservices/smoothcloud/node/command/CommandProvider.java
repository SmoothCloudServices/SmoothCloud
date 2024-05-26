package eu.smoothservices.smoothcloud.node.command;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandProvider {
    @Getter
    private static final HashMap<String, Command> commands = new HashMap<>();

    public CommandProvider() {
//        registerCommand("shutdown", new ShutdownCommand());
//        registerCommand("creategroup", new CreateGroupCommand());
//        registerCommand("deletegroup", new DeleteGroupCommand());
    }

    public void registerCommand(String id, Command command) {
        commands.put(id, command);
    }

    public boolean containsCommand(String command) {
        return commands.containsKey(command.toLowerCase());
    }

    public void call(String[] args) {
        var arguments = new ArrayList<>(Arrays.stream(args).toList());
        var id = arguments.removeFirst();

        if (commands.containsKey(id)) {
            commands.get(id).execute(arguments.toArray(new String[0]));
        }
    }

}
