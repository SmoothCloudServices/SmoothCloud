package eu.smoothcloudservices.smoothcloud.node.setup;

import eu.smoothcloudservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothcloudservices.smoothcloud.api.group.ServerType;
import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.JsonConfig;
import eu.smoothcloudservices.smoothcloud.node.group.CloudGroupImpl;
import eu.smoothcloudservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import static eu.smoothcloudservices.smoothcloud.node.messages.SetupGroupMessages.*;

public class SetupGroup {

    private final TerminalManager terminalManager;
    private final String name;
    private final JsonConfig config;
    private int step = 0;

    private ServerType type;

    @SneakyThrows
    public SetupGroup(String name) {
        this.name = name;
        this.terminalManager = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();
        this.config = new JsonConfig(STR."\{getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath()}/groups", STR."\{name}.cfg");
    }

    @SneakyThrows
    public void setup() {
        SmoothCloudNode.isCreatingGroup = true;
        var input = terminalManager.read();
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
                terminalManager.closeAppend(PREFIX, ERROR);
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

        terminalManager.openAppend(PREFIX, COMPLETED.formatted(name));

        SmoothCloudNode.isCreatingGroup = false;

        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().clearScreen().get();
    }

    private boolean step0(String input) {
        if (!correctMemory(input)) {
            terminalManager.closeAppend(PREFIX, WRONG_MEMORY);
            return false;
        }
        config.setString("Memory", calculateMemory(input));
        terminalManager.openAppend("");
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
            return input.split("mb")[0];
        }
        if (input.toLowerCase().endsWith("gb")) {
            var modifiedInput = input.split("gb")[0];
            var calculatedMegabytes = Integer.parseInt(modifiedInput) * 1024;
            return String.valueOf(calculatedMegabytes);
        }
        return input.split("mb")[0];
    }

    private boolean correctMemory(String input) {
        if (input.toLowerCase().endsWith("mb")) {
            var modifiedInput = input.split("mb")[0];
            if (!halfNumber(modifiedInput)) {
                return Integer.parseInt(modifiedInput) >= 256;
            }
            return false;
        }
        if (input.toLowerCase().endsWith("gb")) {
            var modifiedInput = input.split("gb")[0];
            return !halfNumber(modifiedInput);
        }
        return !halfNumber(input);
    }

    private boolean halfNumber(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            terminalManager.openAppend(PREFIX, HALF_NUMBER);
            return true;
        }
    }

    private void createGroup() {
        if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
            SmoothCloudAPI.getInstance().getGroupProvider().createGroup(new CloudGroupImpl(
                    name, name, "1.20.4", "InternalWrapper", 1, 1, 512, 1024, type, false // todo change with variables
            ));
            terminalManager.closeAppend(STR."&1Group &0\{name}&1 created.");
            return;
        }
        terminalManager.closeAppend(STR."&1Group &0\{name}&1 already exists.");
    }
}
