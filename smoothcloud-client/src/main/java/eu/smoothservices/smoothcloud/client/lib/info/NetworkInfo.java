package eu.smoothservices.smoothcloud.client.lib.info;

import lombok.Getter;

@Getter
public class NetworkInfo {
    private final String serverId;
    private final String hostName;
    private final int port;

    public NetworkInfo(String serverId, String hostName, int port) {
        this.serverId = serverId;
        this.hostName = hostName;
        this.port = port;
    }
}
