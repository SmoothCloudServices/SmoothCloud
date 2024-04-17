package eu.smoothcloudservices.smoothcloud.node.setup;

import com.github.lalyos.jfiglet.FigletFont;
import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.terminal.Color;
import eu.smoothcloudservices.smoothcloud.node.terminal.JLine3Terminal;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class CloudSetup {
    private final JLine3Terminal terminal;
    private final CloudConfig config;
    private int step = 0;

    public CloudSetup() {
        this.terminal = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal();
        this.config = ((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig();
    }

    @SneakyThrows
    public void setup(String[] line) {
        var input = new ArrayList<>(Arrays.stream(line).toList()).removeFirst();
        terminal.write(FigletFont.convertOneLine("SmoothCloud  Setup"));
        switch (step) {
            case 0 -> {
                String eulaURL = "https://www.minecraft.net/en-us/eula";
                presentEula(eulaURL);
            }
            case 1 -> {
                boolean eulaAccepted = getEulaAgreement(input);
                if (!eulaAccepted) {
                    terminal.write(Color.translate("&0CloudSystem &2» &3EULA not accepted. Aborting setup!"));
                    System.exit(0);
                    return;
                }
            }
            case 2 -> {
                if (!chooseNodeIP(input)) {
                    terminal.write(Color.translate("&0CloudSystem &2» &3This IP-Address ist already in use. Please select an another IP-Address"));
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
                terminal.write(Color.translate("&0CloudSystem &2» &3Setup error. Please reinstall your cloud!"));
                Thread.sleep(5000);
                System.exit(0);
            }
        }


        /*if (!chooseWrapperIP(scanner, config)) {
            return false;
        }*/

        step++;
    }

    private void completed() {
        terminal.write(Color.translate("&0CloudSystem &2» &0Setup has been completed."));
        config.save();
    }

    private void presentEula(String eulaUrl) {
        terminal.write(Color.translate("&0CloudSystem &2» &0Do you agree to the Mojang EULA (https://www.minecraft.net/en-us/eula)? Possible answers: yes, no"));
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
                terminal.write(Color.translate("&0CloudSystem &2» &3Please answer with yes or no!"));
            }
        }
    }

    private void chooseNodePort(String input) {
        while (true) {
            terminal.write(Color.translate("&0CloudSystem &2» &0Which port should we use for the node?"));
            int answer = Integer.parseInt(input.toLowerCase());
            boolean portAvailable = checkPortAvailability(answer);
            if (portAvailable) {
                config.set("Port", input);
                return;
            }
            terminal.write(Color.translate("&0CloudSystem &2» &3Port not available. Please choose an other Port!"));
        }
    }

    private void chooseWrapperPort(String input) {
        while (true) {
            terminal.write(Color.translate("&0CloudSystem &2» &0Which port should we use for the wrapper?"));
            int answer = Integer.parseInt(input.toLowerCase());
            boolean portAvailable = checkPortAvailability(answer);
            if (portAvailable) {
                config.set("Wrapper-Port", input);
                return;
            }
            terminal.write(Color.translate("&0CloudSystem &2» &3Port not available. Please choose an other Port!"));
        }
    }

    private boolean chooseNodeIP(String input) {
        while (true) {
            List<InetAddress> inetAddresses = getAllIPAddresses();
            if (inetAddresses.isEmpty()) {
                terminal.write(Color.translate("&0CloudSystem &2» &3No IP addresses available. Aborting setup!"));
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

            terminal.write(Color.translate("&0CloudSystem &2» &0Which IP should we use for the node?"));
            terminal.write(Color.translate(STR."&0CloudSystem &2» &0Available IPs: \{allIps}"));

            if (inetAddresses.contains(input)) {
                config.set("IP-Address", input);
                return true;
            }
            terminal.write(Color.translate("&0CloudSystem &2» &3Please choose an IP address from above!"));
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
                        addresses.add(addressEnumeration.nextElement());
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return addresses;
    }
}