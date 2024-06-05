package eu.smoothservices.smoothcloud.node.setup;

import de.eztxm.config.YamlConfig;
import eu.smoothservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothservices.smoothcloud.api.group.ServerType;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.group.CloudGroupImpl;
import eu.smoothservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import static eu.smoothservices.smoothcloud.node.messages.SetupGroupMessages.*;

public class SetupGroup {

    private final TerminalManager terminalManager;
    private final String name;
    private final YamlConfig config;
    private int step = 0;

    private ServerType type;

    @SneakyThrows
    public SetupGroup(String name) {
        this.name = name;
        this.terminalManager = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminalManager();
        this.config = new YamlConfig(STR."\{getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath()}/groups", STR."\{name}.cfg");
    }

    @SneakyThrows
    public void setup() {
        SmoothCloudNode.isCreatingGroup = true;
        var input = terminalManager.getTerminal().readLine();
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
                terminalManager.getTerminal().writeLine(PREFIX + ERROR);
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
        terminalManager.getTerminal().writeLine(PREFIX + COMPLETED.formatted(name));
        SmoothCloudNode.isCreatingGroup = false;
    }

    private boolean step0(String input) {
        if (!correctMemory(input)) {
            terminalManager.getTerminal().writeLine(PREFIX + WRONG_MEMORY);
            return false;
        }
        config.set("Memory", calculateMemory(input));
        terminalManager.getTerminal().writeLine("");
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
            terminalManager.getTerminal().writeLine(PREFIX + HALF_NUMBER);
            return true;
        }
    }

    private void createGroup() {
        if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
            SmoothCloudAPI.getInstance().getGroupProvider().createGroup(new CloudGroupImpl(
                    name, name, "1.20.4", "InternalWrapper", 1, 1, 512, 1024, type, false // todo change with variables
            ));
            terminalManager.getTerminal().writeLine(STR."\{PREFIX}&1Group &0\{name}&1 created.");
            return;
        }
        terminalManager.getTerminal().writeLine(STR."\{PREFIX}&1Group &0\{name}&1 already exists.");
    }
}
