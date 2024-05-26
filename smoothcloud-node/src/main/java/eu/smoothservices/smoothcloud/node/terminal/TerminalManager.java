package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.terminal.impl.MainTerminal;
import eu.smoothservices.smoothcloud.node.terminal.impl.SetupTerminal;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class TerminalManager {
    private final String prefix = JavaColor.apply("&9Smooth&bCloud &8» &7");
    private ExecutorService service;
    private final Terminal terminal;

    public TerminalManager() {
        this.service = Executors.newCachedThreadPool();
        if (SmoothCloudNode.isSettingUp) {
            this.terminal = new SetupTerminal();
            return;
        }
        this.terminal = new MainTerminal();
    }

    public void start() {
        service.execute(() -> {
            while (true) {
                switch (terminal.readLine()) {
                    case "shutdown" -> {
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}Shutting down cloud..."));
                        System.exit(0);
                    }
                    case "help" -> {
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}----------------Help----------------"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /group create"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /group delete"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /group edit <name>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /module <name> reload"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /module install <id>@<type>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /module remove <id>@<type>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /plugin install <id>@<type> <group>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /plugin remove <id>@<type> <group>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /service start from <group>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /service restart <name>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}- /service stop <name>"));
                        System.out.println(JavaColor.apply(StringTemplate.STR."\{prefix}----------------Help----------------"));
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
