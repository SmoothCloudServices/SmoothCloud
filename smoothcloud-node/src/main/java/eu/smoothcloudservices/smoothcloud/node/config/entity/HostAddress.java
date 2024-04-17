package eu.smoothcloudservices.smoothcloud.node.config.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HostAddress implements Serializable {

    private String hostAddress;
    private String hostName;
    private String hostPort;

    public HostAddress(String hostName, String port) {
        this.hostAddress = STR."\{hostName}:\{port}";
        this.hostName = hostName;
        this.hostPort = port;
    }

}
