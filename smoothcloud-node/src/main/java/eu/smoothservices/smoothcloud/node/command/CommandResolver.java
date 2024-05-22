package eu.smoothservices.smoothcloud.node.command;

public class CommandResolver {

    public CommandResolver() {}

    public boolean resolveCommand(String input) {
        String[] parts = input.split("\\s+");
        if(parts.length == 0) return false;
        String commandKey = parts[0].toLowerCase();
        return CommandProvider.getCommands().containsKey(commandKey);
    }

}
