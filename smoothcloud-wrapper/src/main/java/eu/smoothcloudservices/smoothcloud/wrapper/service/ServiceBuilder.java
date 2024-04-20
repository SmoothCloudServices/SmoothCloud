package eu.smoothcloudservices.smoothcloud.wrapper.service;

import eu.smoothcloudservices.smoothcloud.api.util.ThreadSafe;
import eu.smoothcloudservices.smoothcloud.wrapper.group.Group;
import eu.smoothcloudservices.smoothcloud.wrapper.mojang.ServerJar;

import java.util.UUID;

public class ServiceBuilder {

    private ServerJar serverJar;
    private String hostName;
    private String port;

    public ServiceBuilder(ServerJar serverJar, String port, String hostName) {
        this.serverJar = serverJar;
        this.hostName = hostName;
        this.port = port;
    }

    public ThreadSafe<Service> createService(String serviceName, Group serviceGroup) {
        return ThreadSafe.supply(() -> {
            

            return null;
        });
    }

}
