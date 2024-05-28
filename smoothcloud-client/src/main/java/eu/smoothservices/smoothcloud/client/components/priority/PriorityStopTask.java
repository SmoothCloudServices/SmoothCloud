package eu.smoothservices.smoothcloud.client.components.priority;

import eu.smoothservices.smoothcloud.client.components.INetworkComponent;
import eu.smoothservices.smoothcloud.client.components.MinecraftServer;
import eu.smoothservices.smoothcloud.client.components.ProxyServer;
import eu.smoothservices.smoothcloud.client.components.Wrapper;
import eu.smoothservices.smoothcloud.node.util.task.ScheduledTask;

public class PriorityStopTask implements Runnable{

    private String wrapper;

    private INetworkComponent iNetworkComponent;

    private int time;

    private ScheduledTask scheduledTask;

    public PriorityStopTask(Wrapper wrapper, INetworkComponent iNetworkComponent, int time) {
        this.wrapper = wrapper.getServerId();
        this.iNetworkComponent = iNetworkComponent;
        this.time = time;
    }

    @Override
    public void run() {

        if (iNetworkComponent instanceof ProxyServer) {
            if (!getWrapperInstance().getProxys().containsKey(iNetworkComponent.getServerId()) && scheduledTask != null) {
                scheduledTask.cancel();
            }
        }

        if (iNetworkComponent instanceof MinecraftServer) {
            if (!getWrapperInstance().getServers().containsKey(iNetworkComponent.getServerId()) && scheduledTask != null) {
                scheduledTask.cancel();
            }
        }

        if (iNetworkComponent.getChannel() != null) {
            if (iNetworkComponent instanceof ProxyServer) {
                if (((ProxyServer) iNetworkComponent).getProxyInfo().getOnlineCount() == 0) {
                    time--;
                }
            }

            if (iNetworkComponent instanceof MinecraftServer) {
                if (((MinecraftServer) iNetworkComponent).getServiceInfo().getOnlineCount() == 0) {
                    time--;
                }
            }
        }

        if (time == 0) {
            if (iNetworkComponent instanceof ProxyServer) {
                getWrapperInstance().stopProxy(((ProxyServer) iNetworkComponent));
            }

            if (iNetworkComponent instanceof MinecraftServer) {
                getWrapperInstance().stopServer(((MinecraftServer) iNetworkComponent));
            }

            if (scheduledTask != null) {
                scheduledTask.cancel();
            }
        }
    }

    private Wrapper getWrapperInstance() {
        return null; // Get Wrapper Instance
    }

    public INetworkComponent getiNetworkComponent() {
        return iNetworkComponent;
    }

    public int getTime() {
        return time;
    }

    public ScheduledTask getScheduledTask() {
        return scheduledTask;
    }

    public void setScheduledTask(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }

    public String getWrapper() {
        return wrapper;
    }
}
