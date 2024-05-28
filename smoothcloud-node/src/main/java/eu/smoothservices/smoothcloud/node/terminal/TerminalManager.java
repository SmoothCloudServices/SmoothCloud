package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.setup.CloudSetup;
import lombok.Getter;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static eu.smoothservices.smoothcloud.node.messages.SetupMessages.EULA_ACCEPT;

@Getter
public class TerminalManager {
    private final HashMap<String, Terminal> terminals;
    private final String prefix = "&9Smooth&bCloud &8» &7";
    private final Terminal terminal;
    private CloudSetup cloudSetup;
    private ExecutorService service;

    public TerminalManager() {
        this.terminals = new HashMap<>();
        this.service = Executors.newCachedThreadPool();
        if (!SmoothCloudNode.hasSetup) {
            this.terminal = new Terminal("setup", "&9Smooth&bCloud&8 &8» &7");
            this.terminals.put(this.terminal.getName(), this.terminal);
            this.cloudSetup = new CloudSetup(this);
            return;
        }
        this.terminal = new Terminal("main", prefix);
        this.terminals.put(this.terminal.getName(), this.terminal);
    }

    public void start() {
        if (!SmoothCloudNode.hasSetup) {
            this.terminal.writeLine(EULA_ACCEPT);
        }
        service.execute(() -> {
            while (true) {
                switch (terminal.getName()) {
                    case "main" -> {
                        switch (terminal.readLine()) {
                            case "shutdown" -> {
                                terminal.writeLine("Shutting down cloud...");
                                System.exit(0);
                            }
                            case "help" -> {
                                terminal.writeLine("----------------Help----------------");
                                terminal.writeLine("- group create");
                                terminal.writeLine("- group delete");
                                terminal.writeLine("- group edit <name>");
                                terminal.writeLine("- module <name> reload");
                                terminal.writeLine("- module install <id>@<type>");
                                terminal.writeLine("- module remove <id>@<type>");
                                terminal.writeLine("- plugin install <id>@<type> <group>");
                                terminal.writeLine("- plugin install <id>@<type> <service>");
                                terminal.writeLine("- plugin remove <id>@<type> <group>");
                                terminal.writeLine("- plugin remove <id>@<type> <service>");
                                terminal.writeLine("- service start <group>");
                                terminal.writeLine("- service restart <name>");
                                terminal.writeLine("- service stop <name>");
                                terminal.writeLine("----------------Help----------------");
                            }
                        }
                    }
                    case "setup" -> cloudSetup.start();
                }
            }
        });
    }

    public void shutdown() {
        service.shutdown();
        service = null;
    }
}
