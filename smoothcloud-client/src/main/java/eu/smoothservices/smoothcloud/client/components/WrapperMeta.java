package eu.smoothservices.smoothcloud.client.components;

import lombok.Getter;

@Getter
public class WrapperMeta {
    private final String id;
    private final String hostName;
    private final String user;

    public WrapperMeta(String id, String hostName, String user) {
        this.id = id;
        this.hostName = hostName;
        this.user = user;
    }
}
