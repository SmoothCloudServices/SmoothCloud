package eu.smoothservices.smoothcloud.node.network.components.priority;

import eu.smoothservices.smoothcloud.node.network.components.INetworkComponent;
import eu.smoothservices.smoothcloud.node.network.components.MinecraftServer;
import eu.smoothservices.smoothcloud.node.network.components.ProxyServer;
import eu.smoothservices.smoothcloud.node.network.components.Wrapper;
import eu.smoothservices.smoothcloud.node.util.task.ScheduledTask;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PriorityStopTask implements Runnable{
    private final String wrapper;
    private final INetworkComponent networkComponent;
    private int time;
    @Setter
    private ScheduledTask scheduledTask;

    public PriorityStopTask(Wrapper wrapper, INetworkComponent networkComponent, int time) {
        this.wrapper = wrapper.getServerId();
        this.networkComponent = networkComponent;
        this.time = time;
    }

    @Override
    public void run() {

        if (networkComponent instanceof ProxyServer) {
            if (!getWrapperInstance().getProxys().containsKey(networkComponent.getServerId()) && scheduledTask != null) {
                scheduledTask.cancel();
            }
        }

        if (networkComponent instanceof MinecraftServer) {
            if (!getWrapperInstance().getServers().containsKey(networkComponent.getServerId()) && scheduledTask != null) {
                scheduledTask.cancel();
            }
        }

        if (networkComponent.getChannel() != null) {
            if (networkComponent instanceof ProxyServer) {
                if (((ProxyServer) networkComponent).getProxyInfo().getOnlineCount() == 0) {
                    time--;
                }
            }

            if (networkComponent instanceof MinecraftServer) {
                if (((MinecraftServer) networkComponent).getServiceInfo().getOnlineCount() == 0) {
                    time--;
                }
            }
        }

        if (time == 0) {
            if (networkComponent instanceof ProxyServer) {
                assert getWrapperInstance() != null;
                getWrapperInstance().stopProxy(((ProxyServer) networkComponent));
            }

            if (networkComponent instanceof MinecraftServer) {
                getWrapperInstance().stopServer(((MinecraftServer) networkComponent));
            }

            if (scheduledTask != null) {
                scheduledTask.cancel();
            }
        }
    }

    private Wrapper getWrapperInstance() {
        return null; // Get Wrapper Instance
    }
}
