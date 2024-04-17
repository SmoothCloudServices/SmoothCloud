package eu.smoothcloudservices.smoothcloud.node.setup;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CloudSetup {

    private final TerminalManager manager;
    private final CloudConfig config;
    private int step = 0;

    public CloudSetup() {
        this.config = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig();
        this.manager = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();
    }

    @SneakyThrows
    public void setup() {
        var input = manager.read();
        switch (step) {
            case 0 -> {
                if (step0(input)) return;
            }
            case 1 -> {
                if (step1(input)) return;
            }
            case 2 -> {
            }
            case 3 -> {
                chooseNodePort(input);
            }
            case 4 -> {
                chooseWrapperPort(input);
                completed();
            }
            default -> {
                manager.closeAppend("&0SmoothCloud-Setup &2» &0", "&3Setup error. Please reinstall your cloud!");
                Thread.sleep(2500);
                System.exit(0);
            }
        }
        step++;
        if(step < 4) {
            setup();
        }
    }

    private boolean step0(String input) {
        boolean eulaAccepted = getEulaAgreement(input);
        if (!eulaAccepted) {
            manager.openAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.EULA_NOT_ACCEPTED);
            System.exit(0);
            return true;
        }
        manager.closeAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.EULA_ACCEPTED);
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.isEmpty()) {
            manager.closeAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.NO_CHOOSE_IP);
            System.exit(0);
            return true;
        }
        manager.closeAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.CHOOSE_IP);
        String allIps = null;
        for (String inet4Address : Collections.unmodifiableList(inet4Addresses)) {
            if (allIps == null) {
                allIps = String.valueOf(inet4Address);
            }
            if (!(allIps == null)) {
                allIps = STR."\{allIps}, \{inet4Address}";
            }
        }
        manager.openAppend("&0SmoothCloud-Setup &2» &0", STR."&0\{SetupMessages.CHOOSE_IP_AVAILABLE} \{allIps}");
        return false;
    }

    @SneakyThrows
    private boolean step1(String input) {
        if (!chooseIP(input)) {
            manager.openAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.NO_CHOOSE_IP);
            return true;
        }
        manager.openAppend("&0SmoothCloud-Setup &2» &0", "&0Which port should we use for the node?");
        return false;
    }

    private void completed() {
        manager.closeAppend("&0SmoothCloud-Setup &2» &0", SetupMessages.COMPLETED);
        config.save();
    }

    private boolean getEulaAgreement(String input) {
        String answer = input.toLowerCase();
        if (answer.equals("yes")) {
            config.set("Eula", "true");
            return true;
        }
        return false;
    }

    private void chooseNodePort(String input) {
        int answer = Integer.parseInt(input.toLowerCase());
        boolean portAvailable = checkPortAvailability(answer);
        if (portAvailable) {
            config.set("Port", input);
            return;
        }
        manager.append("&0SmoothCloud-Setup &2» &0", "&3Port not available. Please choose an other Port!");
    }

    private void chooseWrapperPort(String input) {
        manager.append("&0Which port should we use for the wrapper?");
        int answer = Integer.parseInt(input.toLowerCase());
        boolean portAvailable = checkPortAvailability(answer);
        if (portAvailable) {
            config.set("Wrapper-Port", input);
            return;
        }
        manager.append("&0SmoothCloud-Setup &2» &0", "&3Port not available. Please choose an other Port!");
    }

    private boolean chooseIP(String input) throws UnknownHostException {
        List<String> inet4Addresses = getAllIPAddresses();
        if (inet4Addresses.contains(input)) {
            config.set("IP-Address", input);
            return true;
        }
        return false;
    }

    private boolean checkPortAvailability(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private List<String> getAllIPAddresses() {
        List<String> addresses = new ArrayList<>();
        try {
            for(Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                if(networkInterface.isUp()) {
                    for(Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses(); addressEnumeration.hasMoreElements(); ) {
                        InetAddress address = addressEnumeration.nextElement();
                        if(address instanceof Inet4Address fourAddress) {
                            if(address.getHostAddress().split("\\.")[3].equals("1")) {
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