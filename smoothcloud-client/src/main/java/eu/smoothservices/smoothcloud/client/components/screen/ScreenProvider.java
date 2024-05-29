package eu.smoothservices.smoothcloud.client.components.screen;

import eu.smoothservices.smoothcloud.client.components.MinecraftServer;
import eu.smoothservices.smoothcloud.client.components.ProxyServer;
import eu.smoothservices.smoothcloud.client.components.Wrapper;
import eu.smoothservices.smoothcloud.client.network.NetworkUtils;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class ScreenProvider {
    private final Map<String, EnabledScreen> screens = NetworkUtils.newConcurrentHashMap();

    @Setter
    private ServiceId mainServiceId;

    public void handleEnableScreen(ServiceId serviceId, Wrapper wrapper) {
        screens.put(serviceId.getServerId(), new EnabledScreen(serviceId, wrapper));
    }

    public void handleDisableScreen(ServiceId serviceId) {
        screens.remove(serviceId.getServerId());
    }

    public void disableScreen(String server) {
        MinecraftServer minecraftServer = null; // getProxyServer
        if (minecraftServer != null) {
            minecraftServer.getWrapper().disableScreen(minecraftServer.getServiceInfo());
            return;
        }

        ProxyServer proxyServer = null; // getProxyServer
        if (proxyServer != null) {
            proxyServer.getWrapper().disableScreen(proxyServer.getProxyInfo());
        }
    }
}
