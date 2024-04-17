package eu.smoothcloudservices.smoothcloud.node.config.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
public class HostAddress {

    private String hostAddress;
    private String hostName;
    private String hostPort;

    public HostAddress(String hostName, String port) {
        this.hostAddress = STR."\{hostName}:\{port}";
        this.hostName = hostName;
        this.hostPort = port;
    }

    @Override
    public String toString() {
        return """
                {"hostAddress": "%s","hostName": "%s","hostPort": "%s"}
                """.formatted(hostAddress, hostName, hostPort);
    }

    public HostAddress fromString(String input) {
        JSONObject jsonObject = new JSONObject(input);
        return new HostAddress(jsonObject.getString("hostName"), jsonObject.getString("hostPort"));
    }

}
