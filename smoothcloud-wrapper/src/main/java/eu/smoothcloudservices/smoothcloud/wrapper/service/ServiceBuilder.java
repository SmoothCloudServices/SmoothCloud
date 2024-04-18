package eu.smoothcloudservices.smoothcloud.wrapper.service;

import eu.smoothcloudservices.smoothcloud.wrapper.group.Group;
import eu.smoothcloudservices.smoothcloud.wrapper.mojang.ServerJar;
import eu.smoothcloudservices.smoothcloud.wrapper.util.ThreadSafe;

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
        return new ThreadSafe<Service>().supply(() -> {

            UUID uniqueId = UUID.randomUUID();
            String path = STR."static/service-\{uniqueId.toString()}";

            

            return null;
        });
    }

}
