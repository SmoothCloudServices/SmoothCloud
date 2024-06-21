package eu.smoothservices.smoothcloud.node.setup;

import de.eztxm.config.JsonConfig;
import eu.smoothservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.api.group.ICloudGroupProvider;
import eu.smoothservices.smoothcloud.api.group.ServerType;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.group.CloudGroupImpl;
import eu.smoothservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import static eu.smoothservices.smoothcloud.node.messages.SetupGroupMessages.*;
import static eu.smoothservices.smoothcloud.node.messages.SetupMessages.HALF_NUMBER;

public class SetupGroup {

    private final TerminalManager terminalManager;
    private int step = 0;

    private String name;
    private String template;
    private int minMemory;
    private int maxMemory;
    private int maxPlayers;
    private int minOnlineServices;
    private int maxOnlineServices;
    private String permission;
    private boolean maintenance;
    private boolean staticService;
    private int priority;
    private String wrapper;
    private int percentOfPlayersOnGroupToStartNewService;
    private ServerType type;
    private String version;
    private int javaVersion;

    @SneakyThrows
    public SetupGroup() {
        this.terminalManager = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminalManager();
    }

    @SneakyThrows
    public void setup() {
        SmoothCloudNode.isCreatingGroup = true;
        var input = terminalManager.getCloudTerminal().readLine();
        switch (step) {
            case 0 -> {
                if (!step0(input)) return;
            }
            case 1 -> {
                if (!step1(input)) return;
            }
            case 2 -> {
                if (!step2(input)) return;
                completed();
            }
            default -> {
                terminalManager.getCloudTerminal().writeLine(PREFIX + ERROR);
                Thread.sleep(2000);
                System.exit(0);
            }
        }
        step++;
        if(step < 4) {
            setup();
        }
    }

    @SneakyThrows
    private void completed() {
        createGroup();
        terminalManager.getCloudTerminal().writeLine(PREFIX + COMPLETED.formatted(name));
        SmoothCloudNode.isCreatingGroup = false;
    }

    private boolean step0(String input) {
        if (!correctMemory(input)) {
            terminalManager.getCloudTerminal().writeLine(PREFIX + WRONG_MEMORY);
            return false;
        }
        terminalManager.getCloudTerminal().writeLine("");
        return true;
    }

    private boolean step1(String input) {
        // todo
        return true;
    }

    private boolean step2(String input) {
        // todo
        return true;
    }

    private void createGroup() {
        if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
            ICloudGroupProvider provider = SmoothCloudAPI.getInstance().getGroupProvider();
            ICloudGroup group = new CloudGroupImpl(
                    name,
                    template,
                    minMemory,
                    maxMemory,
                    maxPlayers,
                    minOnlineServices,
                    maxOnlineServices,
                    permission,
                    maintenance,
                    staticService,
                    priority,
                    wrapper,
                    percentOfPlayersOnGroupToStartNewService,
                    type,
                    version,
                    javaVersion
            );
            provider.createGroup(group);
            JsonConfig groupConfig = new JsonConfig("groups", STR."\{name.toLowerCase()}.json");
            groupConfig.set("name", name);
            groupConfig.set("template", template);
            groupConfig.set("minMemory", minMemory);
            groupConfig.set("maxMemory", maxMemory);
            groupConfig.set("maxPlayers", maxPlayers);
            groupConfig.set("minOnlineServices", minOnlineServices);
            groupConfig.set("maxOnlineServices", maxOnlineServices);
            groupConfig.set("permission", permission);
            groupConfig.set("maintenance", maintenance);
            groupConfig.set("static", staticService);
            groupConfig.set("priority", priority);
            groupConfig.set("wrapperName", wrapper);
            groupConfig.set("percentOfPlayersOnGroupToStartNewService", percentOfPlayersOnGroupToStartNewService);
            groupConfig.set("ServerType", type.name().toUpperCase());
            groupConfig.set("Version", version);
            groupConfig.set("Java-Version", javaVersion);
            terminalManager.getCloudTerminal().writeLine(STR."\{PREFIX}&1Group &0\{name}&1 created.");
            return;
        }
        terminalManager.getCloudTerminal().writeLine(STR."\{PREFIX}&1Group &0\{name}&1 already exists.");
    }

    private String calculateMemory(String input) {
        if (input.toLowerCase().endsWith("mb")) {
            return input.toLowerCase().split("mb")[0];
        }
        if (input.toLowerCase().endsWith("gb")) {
            var modifiedInput = input.toLowerCase().split("gb")[0];
            var calculatedMegabytes = Integer.parseInt(modifiedInput) * 1024;
            return String.valueOf(calculatedMegabytes);
        }
        return input.toLowerCase().split("mb")[0];
    }

    private boolean correctMemory(String input) {
        if (input.toLowerCase().endsWith("mb")) {
            var modifiedInput = input.toLowerCase().split("mb")[0];
            if (!halfNumber(modifiedInput)) {
                return Integer.parseInt(modifiedInput) >= 256;
            }
            return false;
        }
        if (input.toLowerCase().endsWith("gb")) {
            var modifiedInput = input.toLowerCase().split("gb")[0];
            return !halfNumber(modifiedInput);
        }
        return !halfNumber(input);
    }

    private boolean halfNumber(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            terminalManager.getCloudTerminal().writeLine(HALF_NUMBER);
            return true;
        }
    }
}
