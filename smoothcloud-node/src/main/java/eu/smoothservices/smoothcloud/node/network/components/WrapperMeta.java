package eu.smoothservices.smoothcloud.node.network.components;

public class WrapperMeta {

    private String id;

    private String hostName;

    private String user;

    public WrapperMeta(String id, String hostName, String user) {
        this.id = id;
        this.hostName = hostName;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getHostName() {
        return hostName;
    }

    public String getUser() {
        return user;
    }
}
