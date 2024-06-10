package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.setup.CloudSetup;
import lombok.Getter;
import lombok.Setter;
import org.jline.reader.impl.completer.StringsCompleter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static eu.smoothservices.smoothcloud.node.messages.SetupMessages.EULA_ACCEPT;

@Getter
public class TerminalManager {
    private final String prefix = "&9Smooth&bCloud &8» &7";
    @Setter
    private HashMap<String, CloudTerminal> terminals;
    private CloudTerminal cloudTerminal;
    private CloudSetup cloudSetup;
    private ExecutorService service;

    public TerminalManager() {
        this.terminals = new HashMap<>();
        this.service = Executors.newCachedThreadPool();
        if (SmoothCloudNode.needSetup) {
            this.cloudTerminal = new CloudTerminal("setup", prefix, new StringsCompleter());
            this.terminals.put(this.cloudTerminal.getName(), this.cloudTerminal);
            this.cloudSetup = new CloudSetup(this);
            return;
        }
        createMainTerminal();
        this.terminals.put(this.cloudTerminal.getName(), this.cloudTerminal);
    }

    public CloudTerminal createMainTerminal() {
        List<String> commands = new ArrayList<>();
        commands.add("group");
        commands.add("module");
        commands.add("plugin");
        commands.add("service");
        return this.cloudTerminal = new CloudTerminal("main", prefix, new StringsCompleter(commands));
    }

    public void start() {
        if (SmoothCloudNode.needSetup) {
            this.cloudTerminal.writeLine(EULA_ACCEPT);
            this.cloudTerminal.userInput();
        }
        service.execute(() -> {
            while (true) {
                switch (cloudTerminal.getName()) {
                    case "main" -> {
                        switch (cloudTerminal.readLine()) {
                            case "shutdown" -> {
                                cloudTerminal.writeLine("Shutting down cloud...");
                                System.exit(0);
                            }
                            case "help" -> {
                                cloudTerminal.writeLine("----------------Help----------------");
                                cloudTerminal.writeLine("- group create");
                                cloudTerminal.writeLine("- group delete");
                                cloudTerminal.writeLine("- group edit <name>");
                                cloudTerminal.writeLine("- module <name> reload");
                                cloudTerminal.writeLine("- module install <id>@<type>");
                                cloudTerminal.writeLine("- module remove <id>@<type>");
                                cloudTerminal.writeLine("- plugin install <id>@<type> <group>");
                                cloudTerminal.writeLine("- plugin install <id>@<type> <service>");
                                cloudTerminal.writeLine("- plugin remove <id>@<type> <group>");
                                cloudTerminal.writeLine("- plugin remove <id>@<type> <service>");
                                cloudTerminal.writeLine("- service start <group>");
                                cloudTerminal.writeLine("- service restart <name>");
                                cloudTerminal.writeLine("- service stop <name>");
                                cloudTerminal.writeLine("----------------Help----------------");
                            }
                        }
                    }
                    case "setup" -> cloudSetup.start();
                }
            }
        });
    }

    public void changeTerminal(CloudTerminal cloudTerminal) {
        this.cloudTerminal = cloudTerminal;
    }

    public void shutdown() {
        service.shutdown();
        service = null;
    }
}
