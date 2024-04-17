package eu.smoothcloudservices.smoothcloud.node.setup;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.terminal.Color;
import eu.smoothcloudservices.smoothcloud.node.terminal.TerminalManager;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.util.*;

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
                String eulaURL = "https://www.minecraft.net/en-us/eula";
                presentEula(eulaURL);
            }
            case 1 -> {
                boolean eulaAccepted = getEulaAgreement(input);
                if (!eulaAccepted) {
                    manager.append("&3EULA not accepted. Aborting setup!");
                    System.exit(0);
                    return;
                }
            }
            case 2 -> {
                if (!chooseNodeIP(input)) {
                    manager.append("&3This IP-Address ist already in use. Please select an another IP-Address");
                }
            }
            case 3 -> {
                chooseNodePort(input);
            }
            case 4 -> {
                chooseWrapperPort(input);
                completed();
            }
            default -> {
                manager.append("&3Setup error. Please reinstall your cloud!");
                Thread.sleep(5000);
                System.exit(0);
            }
        }


        /*if (!chooseWrapperIP(scanner, config)) {
            return false;
        }*/

        step++;
        if(step < 4) {
            this.setup();
        }
    }

    private void completed() {
        manager.append("&0Setup has been completed.");
        config.save();
    }

    private void presentEula(String eulaUrl) {
        manager.append("&0Do you agree to the Mojang EULA (https://www.minecraft.net/en-us/eula)? Possible answers: yes, no");
    }

    private boolean getEulaAgreement(String input) {
        while (true) {
            String answer = input.toLowerCase();
            if(answer.equals("yes") || answer.equals("y")) {
                config.set("Eula", "true");
                return true;
            } else if(answer.contains("no") || answer.contains("n")) {
                return false;
            } else {
                manager.append("&3Please answer with yes or no!");
            }
        }
    }

    private void chooseNodePort(String input) {
        while (true) {
            manager.append("&0Which port should we use for the node?");
            int answer = Integer.parseInt(input.toLowerCase());
            boolean portAvailable = checkPortAvailability(answer);
            if (portAvailable) {
                config.set("Port", input);
                return;
            }
            manager.append("&3Port not available. Please choose an other Port!");
        }
    }

    private void chooseWrapperPort(String input) {
        while (true) {
            manager.append("&0Which port should we use for the wrapper?");
            int answer = Integer.parseInt(input.toLowerCase());
            boolean portAvailable = checkPortAvailability(answer);
            if (portAvailable) {
                config.set("Wrapper-Port", input);
                return;
            }
            manager.append("&3Port not available. Please choose an other Port!");
        }
    }

    private boolean chooseNodeIP(String input) {
        while (true) {
            List<InetAddress> inetAddresses = getAllIPAddresses();
            if (inetAddresses.isEmpty()) {
                manager.append("&3No IP addresses available. Aborting setup!");
                return false;
            }

            String allIps;
            allIps = null;
            for (InetAddress inetAddress : Collections.unmodifiableList(inetAddresses)) {
                if (allIps == null) {
                    allIps = String.valueOf(inetAddress);
                }
                if (!(allIps == null)) {
                    allIps = STR."\{allIps}, \{inetAddress}";
                }
            }

            manager.append("&0Which IP should we use for the node?");
            manager.append(STR."&0Available IPs: \{allIps}");

            if (inetAddresses.contains(input)) {
                config.set("IP-Address", input);
                return true;
            }
            manager.append("&3Please choose an IP address from above!");
        }
    }


    // For first internal wrapper the same ip

    /*private boolean chooseWrapperIP(Scanner scanner, CloudConfig config) {
        while (true) {
            List<String> inetAddresses = getAllIPAddresses();
            if (inetAddresses.isEmpty()) {
                terminal.write(Color.translate("&0CloudSystem &2» &3No IP addresses available. Aborting setup!"));
                return false;
            }

            String allIps;
            allIps = null;
            for (String inetAddress : Collections.unmodifiableList(inetAddresses)) {
                if (allIps == null) {
                    allIps = inetAddress;
                }
                if (!(allIps == null)) {
                    allIps = STR."\{allIps}, \{inetAddress}";
                }
            }

            terminal.write(Color.translate("&0CloudSystem &2» &0Which IP should we use for the wrapper?"));
            terminal.write(Color.translate(STR."&0CloudSystem &2» &0Available IPs: \{allIps}"));

            if (inetAddresses.contains(scanner.nextLine())) {

                return true;
            }
            terminal.write(Color.translate("&0CloudSystem &2» &3Please choose an IP address from above!"));
        }
    }*/

    private boolean checkPortAvailability(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            socket.close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private List<InetAddress> getAllIPAddresses() {
        List<InetAddress> addresses = new ArrayList<>();
        try {
            for(Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                if(networkInterface.isUp()) {
                    for(Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses(); addressEnumeration.hasMoreElements(); ) {
                        InetAddress address = addressEnumeration.nextElement();
                        if(address instanceof Inet4Address) {
                            if(address.getHostAddress().split("\\.")[3].equals("1")) {
                                continue;
                            }
                            addresses.add(address);
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