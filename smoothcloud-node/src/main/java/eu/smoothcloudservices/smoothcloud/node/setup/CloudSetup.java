package eu.smoothcloudservices.smoothcloud.node.setup;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.config.entity.EulaAgreement;
import eu.smoothcloudservices.smoothcloud.node.config.entity.HostAddress;
import eu.smoothcloudservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static eu.smoothcloudservices.smoothcloud.node.setup.SetupMessages.*;

public class CloudSetup {

    private final TerminalManager terminalManager;
    private final CloudConfig config;
    private int step = 0;

    private String host;

    public CloudSetup() {
        this.config = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig();
        this.terminalManager = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();
    }

    @SneakyThrows
    public void setup() {
        SmoothCloudNode.isSettingUp = true;
        var input = terminalManager.read();
        switch (step) {
            case 0 -> {
                if (step0(input)) return;
            }
            case 1 -> {
                if (step1(input)) return;
            }
            case 2 -> {
                if (step2(input)) return;
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
        terminalManager.closeAppend(PREFIX, COMPLETED);

        this.config.save();

        ((SmoothCloudNode) SmoothCloudNode.getInstance()).startCloud();
    }

    private boolean step0(String input) {
        boolean eulaAccepted = getEulaAgreement(input);
        if (!eulaAccepted) {
            terminalManager.openAppend(PREFIX, EULA_NOT_ACCEPTED);
            System.exit(0);
            return true;
        }
        terminalManager.closeAppend(PREFIX, EULA_ACCEPTED);
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.isEmpty()) {
            terminalManager.closeAppend(PREFIX, NO_CHOOSE_IP);
            System.exit(0);
            return true;
        }
        terminalManager.closeAppend(PREFIX, CHOOSE_IP);
        StringBuilder allIps = null;
        for (String inet4Address : Collections.unmodifiableList(inet4Addresses)) {
            if (allIps == null) {
                allIps = new StringBuilder(String.valueOf(inet4Address)).append(", ");
            }
            allIps.append(inet4Address);
        }
        terminalManager.openAppend(PREFIX, CHOOSE_IP_AVAILABLE + allIps);
        return false;
    }

    @SneakyThrows
    private boolean step1(String input) {
        if (!chooseIP(input)) {
            terminalManager.openAppend(PREFIX, CHOOSE_IP_NOT_EXISTS);
            return true;
        }
        terminalManager.openAppend(PREFIX, CHOOSE_PORT);
        return false;
    }

    private boolean step2(String input) {
        boolean portAvailable = checkPortAvailability(Integer.parseInt(input.toLowerCase()));
        if (!portAvailable) {
            terminalManager.openAppend(PREFIX, CHOOSE_PORT_NOT_EXISTS);
            return true;
        }
        config.setAddress(new HostAddress(host, input));
        terminalManager.closeAppend(PREFIX, SAVE_HOST_PORT);
        return false;
    }

    private boolean getEulaAgreement(String input) {
        String answer = input.toLowerCase();
        if (answer.equals("yes")) {
            config.setAgreement(new EulaAgreement(true));
            return true;
        }
        return false;
    }

    private boolean chooseIP(String input) {
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.contains(input)) {
            host = input;
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
}