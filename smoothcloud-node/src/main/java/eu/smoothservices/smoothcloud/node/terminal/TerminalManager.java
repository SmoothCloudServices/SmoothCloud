package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class TerminalManager {
    private final String prefix = JavaColor.apply("&9Smooth&bCloud &8» &7");
    private final Terminal terminal;
    private ExecutorService service;

    public TerminalManager() {
        if (SmoothCloudNode.isSettingUp) {
            this.terminal = new Terminal("setup", "&9Smooth&bCloud&8-&2Setup &8» &7");
            return;
        }
        this.terminal = new Terminal("main", prefix);
        this.service = Executors.newCachedThreadPool();
    }

    public void start() {
        service.execute(() -> {
            while (true) {
                switch (terminal.readLine()) {
                    case "shutdown" -> {
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}Shutting down cloud..."));
                        System.exit(0);
                    }
                    case "help" -> {
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}----------------Help----------------"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /group create"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /group delete"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /group edit <name>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /module <name> reload"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /module install <id>@<type>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /module remove <id>@<type>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /plugin install <id>@<type> <group>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /plugin remove <id>@<type> <group>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /service start from <group>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /service restart <name>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}- /service stop <name>"));
                        terminal.writeLine(JavaColor.apply(StringTemplate.STR."\{prefix}----------------Help----------------"));
                    }
                }
            }
        });
    }

    public void shutdown() {
        service.shutdown();
        service = null;
    }
}
