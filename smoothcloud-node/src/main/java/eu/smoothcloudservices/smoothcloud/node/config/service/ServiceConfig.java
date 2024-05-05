package eu.smoothcloudservices.smoothcloud.node.config.service;

import eu.smoothcloudservices.smoothcloud.node.util.document.Document;

public class ServiceConfig {

    private boolean hideServer;
    private String extra;
    private Document properties;
    private long startup;

    public ServiceConfig(boolean hideServer, String extra, Document properties, long startup) {
        this.hideServer = hideServer;
        this.extra = extra;
        this.properties = properties;
        this.startup = startup;
    }

    public Document getProperties() {
        return properties;
    }

    public long getStartup() {
        return startup;
    }

    public String getExtra() {
        return extra;
    }

    public boolean isHideServer() {
        return hideServer;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setHideServer(boolean hideServer) {
        this.hideServer = hideServer;
    }

    public void setProperties(Document properties) {
        this.properties = properties;
    }

    public void setStartup(long startup) {
        this.startup = startup;
    }
}
