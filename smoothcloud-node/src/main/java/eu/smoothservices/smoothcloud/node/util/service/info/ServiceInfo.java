package eu.smoothservices.smoothcloud.node.util.service.info;

import com.google.gson.reflect.TypeToken;
import eu.smoothservices.smoothcloud.node.config.service.ServiceConfig;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothservices.smoothcloud.node.util.service.ServiceState;
import eu.smoothservices.smoothcloud.node.util.service.template.Template;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.List;

@Getter
public class ServiceInfo {

    public static final Type TYPE = new TypeToken<ServiceInfo>() {}.getType();
    private ServiceId serviceId;
    private String host;
    private int port;
    private boolean online;
    private List<String> players;
    private int memory;
    private String motd;
    private int onlineCount;
    private int maxPlayers;
    private ServiceState serviceState;
    private ServiceConfig serviceConfig;
    private Template template;

    public ServiceInfo(ServiceId serviceId, String host, int port, boolean online, List<String> players, int memory, String motd, int onlineCount, int maxPlayers, ServiceState serviceState, ServiceConfig serviceConfig, Template template) {
        this.serviceId = serviceId;
        this.host = host;
        this.port = port;
        this.online = online;
        this.players = players;
        this.memory = memory;
        this.motd = motd;
        this.onlineCount = onlineCount;
        this.maxPlayers = maxPlayers;
        this.serviceState = serviceState;
        this.serviceConfig = serviceConfig;
        this.template = template;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isIngame() {
        if(serviceState == null) {
            serviceState = ServiceState.LOBBY;
        }

        if(motd == null) {
            motd = "null";
        }

        return serviceState == ServiceState.INGAME || (motd.equalsIgnoreCase("INGAME") || motd.equalsIgnoreCase("RUNNING"));
    }
}
