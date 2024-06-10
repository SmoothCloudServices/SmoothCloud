package eu.smoothservices.smoothcloud.node.ezterminal;

import de.eztxm.terminal.Terminal;
import de.eztxm.terminal.command.Command;
import de.eztxm.terminal.command.CommandMap;
import de.eztxm.terminal.util.JavaColor;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class EzLibTerminal {
    private final Terminal terminal;

    public EzLibTerminal() {
        HashMap<String, Command> commands = new HashMap<>();
        this.terminal = new Terminal(
                "SmoothCloud - 0.0.0-INDEV",
                JavaColor.apply("&9Smooth&bCloud &8» &7"),
                new CommandMap(commands));
    }
}
