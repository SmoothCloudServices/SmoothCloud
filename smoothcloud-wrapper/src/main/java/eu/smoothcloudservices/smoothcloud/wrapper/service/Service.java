package eu.smoothcloudservices.smoothcloud.wrapper.service;

import eu.smoothcloudservices.smoothcloud.wrapper.group.Group;
import lombok.SneakyThrows;

public class Service {

    private String path;
    private Group group;

    private String serviceName;
    private String hostName;
    private int port;
    private int maxMem;

    private Process service;

    @SneakyThrows
    public Service(String path, Group group, String serviceName, String hostName, int port, int maxMem) {
        this.path = path;
        this.group = group;

        this.serviceName = serviceName;
        this.hostName = hostName;
        this.port = port;
        this.maxMem = maxMem;

        this.service = Runtime.getRuntime().exec(STR."java -jar \{path}");
    }

    public void stop() {
        if(service != null) {
            service.destroy();
        }
    }

}
