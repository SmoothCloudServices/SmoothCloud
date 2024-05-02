package eu.smoothcloudservices.smoothcloud.node.util;

public class ConnectableAddress {

    private String hostName;
    private int port;

    public ConnectableAddress(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getHostName() {
        return hostName;
    }
}
