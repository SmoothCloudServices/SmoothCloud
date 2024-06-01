package eu.smoothservices.smoothcloud.node.setup;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.config.Language;
import eu.smoothservices.smoothcloud.node.terminal.Terminal;
import eu.smoothservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.util.*;

import static eu.smoothservices.smoothcloud.node.messages.SetupMessages.*;

public class CloudSetup {
    private final TerminalManager terminalManager;
    private int step = 0;

    public CloudSetup(TerminalManager terminalManager) {
        this.terminalManager = terminalManager;
    }

    @SneakyThrows
    public void start() {
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
            }
            case 3 -> {
                if (!step3(input)) return;
                complete();
            }
            default -> {
                terminalManager.getTerminal().writeLine(ERROR);
                Thread.sleep(1000);
                System.exit(0);
            }
        }
        step++;
        if(step < 4) {
            start();
        }
    }

    @SneakyThrows
    private void complete() {
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().setLanguage(Language.EN);
        terminalManager.getTerminal().writeLine(COMPLETED);
        Thread.sleep(1000);
        terminalManager.getTerminal().clearScreen();
        Terminal terminal = new Terminal("main", terminalManager.getPrefix());
        HashMap<String, Terminal> terminals = new HashMap<>();
        terminals.put("main", terminal);
        terminalManager.setTerminals(terminals);
        terminalManager.changeTerminal(terminal);
        SmoothCloudNode.hasSetup = true;
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).startCloud();
    }

    private boolean step0(String input) {
        boolean eulaAccepted = getEulaAgreement(input);
        if (!eulaAccepted) {
            terminalManager.getTerminal().writeLine(EULA_NOT_ACCEPTED);
            System.exit(0);
            return false;
        }
        terminalManager.getTerminal().writeLine(EULA_ACCEPTED);
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.isEmpty()) {
            terminalManager.getTerminal().writeLine(NO_CHOOSE_IP);
            System.exit(0);
            return false;
        }
        terminalManager.getTerminal().writeLine(CHOOSE_IP);
        StringBuilder allIps = new StringBuilder();
        for (String inet4Address : Collections.unmodifiableList(inet4Addresses)) {
            if (inet4Addresses.getLast().equalsIgnoreCase(inet4Address)) {
                allIps.append(inet4Address);
                continue;
            }
            allIps.append(inet4Address).append(", ");
        }
        terminalManager.getTerminal().writeLine(CHOOSE_IP_AVAILABLE + allIps);
        return true;
    }

    @SneakyThrows
    private boolean step1(String input) {
        if (!chooseIP(input)) {
            terminalManager.getTerminal().writeLine(CHOOSE_IP_NOT_EXISTS);
            return false;
        }
        terminalManager.getTerminal().writeLine(CHOOSE_PORT);
        return true;
    }

    private boolean step2(String input) {
        boolean portAvailable = checkPortAvailability(Integer.parseInt(input.toLowerCase()));
        if (!portAvailable) {
            terminalManager.getTerminal().writeLine(CHOOSE_PORT_NOT_EXISTS);
            return false;
        }
        try {
            ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().setPort(Integer.parseInt(input));
            terminalManager.getTerminal().writeLine(CHOOSE_MEMORY);
            return true;
        } catch (NumberFormatException e) {
            terminalManager.getTerminal().writeLine(WRONG_INPUT);
            return false;
        }
    }

    private boolean step3(String input) {
        if (!correctMemory(input)) {
            terminalManager.getTerminal().writeLine(WRONG_MEMORY);
            return false;
        }
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().setMemory(calculateMemory(input));
        terminalManager.getTerminal().writeLine(SAVED);
        return true;
    }

    private boolean getEulaAgreement(String input) {
        String answer = input.toLowerCase();
        if (!answer.equals("yes")) {
            return false;
        }
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().setEulaAgreement(true);
        return true;
    }

    private boolean chooseIP(String input) {
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.contains(input)) {
            ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().setHost(input);
            return true;
        }
        return false;
    }

    private boolean checkPortAvailability(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            socket.close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private List<String> getAllIPAddresses() {
        List<String> addresses = new ArrayList<>();
        addresses.add("127.0.0.1");
        try {
            for (Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (networkInterface.isUp()) {
                    for (Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses(); addressEnumeration.hasMoreElements(); ) {
                        InetAddress address = addressEnumeration.nextElement();
                        if (address instanceof Inet4Address fourAddress) {
                            if (address.getHostAddress().split("\\.")[3].equals("1")) {
                                continue;
                            }
                            if (!addresses.contains(fourAddress.getHostAddress())) {
                                addresses.add(fourAddress.getHostAddress());
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return addresses;
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
            terminalManager.getTerminal().writeLine(HALF_NUMBER);
            return true;
        }
    }
}