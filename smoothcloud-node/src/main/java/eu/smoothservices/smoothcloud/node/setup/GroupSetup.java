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

public class GroupSetup {

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
    public GroupSetup() {
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
            }
            case 3 -> {
                if (!step3(input)) return;
            }
            case 4 -> {
                if (!step4(input)) return;
            }
            case 5 -> {
                if (!step5(input)) return;
            }
            case 6 -> {
                if (!step6(input)) return;
            }
            case 7 -> {
                if (!step7(input)) return;
            }
            case 8 -> {
                if (!step8(input)) return;
            }
            case 9 -> {
                if (!step9(input)) return;
            }
            case 10 -> {
                if (!step10(input)) return;
            }
            case 11 -> {
                if (!step11(input)) return;
            }
            case 12 -> {
                if (!step12(input)) return;
            }
            case 13 -> {
                if (!step13(input)) return;
            }
            case 14 -> {
                if (!step14(input)) return;
            }
            case 15 -> {
                if (!step15(input)) return;
                completed();
            }
            default -> {
                terminalManager.getCloudTerminal().writeLine(PREFIX + ERROR);
                Thread.sleep(2000);
                System.exit(0);
                return;
            }
        }
        step++;
        if(step < 15) {
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
        if (!onlyLettersOrDigit(input)) {
            // Send no special chars message
            return false;
        }
        this.name = input;
        // Send next step message
        return true;
    }

    private boolean step1(String input) {
        if (!onlyLettersOrDigit(input)) {
            // Send no special chars message
            return false;
        }
        this.template = input;
        // Send next step message
        return true;
    }

    private boolean step2(String input) {
        if (!correctMemory(input)) {
            // Send the not correct memory message
            return false;
        }
        this.minMemory = calculateMemoryInMegaBytes(input);
        // Send next step message
        return true;
    }

    private boolean step3(String input) {
        if (!correctMemory(input)) {
            // Send the not correct memory message
            return false;
        }
        this.maxMemory = calculateMemoryInMegaBytes(input);
        // Send next step message
        return true;
    }

    private boolean step4(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        this.maxPlayers = Integer.parseInt(input);
        // Send next step message
        return true;
    }

    private boolean step5(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        this.minOnlineServices = Integer.parseInt(input);
        // Send next step message
        return true;
    }

    private boolean step6(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        this.maxOnlineServices = Integer.parseInt(input);
        // Send next step message
        return true;
    }

    private boolean step7(String input) {
        if (!canBeSplit(input, ".")) {
            // Send the need point message
            return false;
        }
        this.permission = input;
        // Send next step message
        return true;
    }

    private boolean step8(String input) {
        if (!onlyLetters(input)) {
            // Send the only letters message
            return false;
        }
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            this.maintenance = Boolean.parseBoolean(input.toLowerCase());
            // Send next step message
            return true;
        }
        // Send the not true or false message
        return false;
    }

    private boolean step9(String input) {
        if (!onlyLetters(input)) {
            // Send the only letters message
            return false;
        }
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            this.maintenance = Boolean.parseBoolean(input.toLowerCase());
            // Send next step message
            return true;
        }
        // Send the not true or false message
        return false;
    }

    private boolean step10(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        this.priority = Integer.parseInt(input);
        // Send next step message
        return true;
    }

    private boolean step11(String input) {
        if (!onlyLetters(input)) {
            // Send the only letters message
            return false;
        }
        // Check if wrapper exists
        this.wrapper = input;
        // Send next step message
        return true;
    }

    private boolean step12(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        this.percentOfPlayersOnGroupToStartNewService = Integer.parseInt(input);
        // Send next step message
        return true;
    }

    private boolean step13(String input) {
        if (!onlyLetters(input)) {
            // Send the only letters message
            return false;
        }
        if (!containsValue(ServerType.class, input.toUpperCase())) {
            // Send the not a server type message
            return false;
        }
        this.type = ServerType.valueOf(input.toUpperCase());
        // Send next step message
        return true;
    }

    private boolean step14(String input) {
        if (!canBeSplit(input, ".")) {
            // Send the need point message
            return false;
        }
        // Check if the version is supported
        this.version = input;
        // Send next step message
        return true;
    }

    private boolean step15(String input) {
        if (!onlyDigits(input)) {
            // Send the only digits message
            return false;
        }
        if (input.length() > 2) {
            // Send digits too long for a java-version
            return false;
        }
        this.javaVersion = Integer.parseInt(input);
        return true;
    }

    private void createGroup() {
        if (!SmoothCloudAPI.getInstance().getGroupProvider().existsGroup(name)) {
            ICloudGroupProvider provider = SmoothCloudAPI.getInstance().getGroupProvider();
            ICloudGroup group = new CloudGroupImpl(
                    this.name,
                    this.template,
                    this.minMemory,
                    this.maxMemory,
                    this.maxPlayers,
                    this.minOnlineServices,
                    this.maxOnlineServices,
                    this.permission,
                    this.maintenance,
                    this.staticService,
                    this.priority,
                    this.wrapper,
                    this.percentOfPlayersOnGroupToStartNewService,
                    this.type,
                    this.version,
                    this.javaVersion
            );
            provider.createGroup(group);
            JsonConfig groupConfig = new JsonConfig("groups", STR."\{this.name.toLowerCase()}.json");
            groupConfig.set("name", this.name);
            groupConfig.set("template", this.template);
            groupConfig.set("minMemory", this.minMemory);
            groupConfig.set("maxMemory", this.maxMemory);
            groupConfig.set("maxPlayers", this.maxPlayers);
            groupConfig.set("minOnlineServices", this.minOnlineServices);
            groupConfig.set("maxOnlineServices", this.maxOnlineServices);
            groupConfig.set("permission", this.permission);
            groupConfig.set("maintenance", this.maintenance);
            groupConfig.set("static", this.staticService);
            groupConfig.set("priority", this.priority);
            groupConfig.set("wrapperName", this.wrapper);
            groupConfig.set("percentOfPlayersOnGroupToStartNewService", this.percentOfPlayersOnGroupToStartNewService);
            groupConfig.set("ServerType", this.type.name().toUpperCase());
            groupConfig.set("Version", this.version);
            groupConfig.set("Java-Version", this.javaVersion);
            terminalManager.getCloudTerminal().writeLine(STR."\{PREFIX}&1Group &0\{this.name}&1 created.");
            return;
        }
        terminalManager.getCloudTerminal().writeLine(STR."\{PREFIX}&1Group &0\{this.name}&1 already exists.");
    }

    private boolean onlyLettersOrDigit(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean onlyLetters(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean onlyDigits(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean canBeSplit(String text, String delimiter) {
        String[] parts = text.split(delimiter);
        return parts.length > 1;
    }

    private <E extends Enum<E>> boolean containsValue(Class<E> enumClass, String value) {
        // Überprüfen, ob das Enum den gegebenen Wert enthält
        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private int calculateMemoryInMegaBytes(String input) {
        if (input.toLowerCase().endsWith("mb")) {
            return Integer.parseInt(input.toLowerCase().split("mb")[0]);
        }
        if (input.toLowerCase().endsWith("gb")) {
            var modifiedInput = input.toLowerCase().split("gb")[0];
            return Integer.parseInt(modifiedInput) * 1024;
        }
        return Integer.parseInt(input.replace("mb", ""));
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
