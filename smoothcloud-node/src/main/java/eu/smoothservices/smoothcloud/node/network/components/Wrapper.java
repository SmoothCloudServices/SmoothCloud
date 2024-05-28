package eu.smoothservices.smoothcloud.node.network.components;

import eu.smoothservices.smoothcloud.node.network.lib.WrapperInfo;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.out.PacketOutExecuteCommand;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.out.PacketOutExecuteServerCommand;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.out.PacketOutStartProxy;
import eu.smoothservices.smoothcloud.node.util.NetworkUtils;
import eu.smoothservices.smoothcloud.node.util.lib.CollectionWrapper;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;
import eu.smoothservices.smoothcloud.node.util.lib.Quad;
import eu.smoothservices.smoothcloud.node.util.lib.threading.Runnabled;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import eu.smoothservices.smoothcloud.node.util.service.ServiceProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;
import eu.smoothservices.smoothcloud.node.util.service.template.Template;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Wrapper implements INetworkComponent {

    private final java.util.Map<String, ProxyServer> proxys = NetworkUtils.newConcurrentHashMap();
    private final java.util.Map<String, MinecraftServer> servers = NetworkUtils.newConcurrentHashMap();
    private final java.util.Map<String, CloudService> cloudServers = NetworkUtils.newConcurrentHashMap();
    // Group, ServiceId
    private final java.util.Map<String, Quad<Integer, Integer, ServiceId, Template>> waitingServices = NetworkUtils.newConcurrentHashMap();
    private Channel channel;
    private WrapperInfo wrapperInfo;
    private WrapperMeta networkInfo;
    private boolean ready;
    private double cpuUsage = -1;
    private int maxMemory = 0;

    private String serverId;

    public Wrapper(WrapperMeta networkInfo) {
        this.serverId = networkInfo.getId();
        this.networkInfo = networkInfo;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Map<String, CloudService> getCloudServers() {
        return cloudServers;
    }

    public Map<String, MinecraftServer> getServers() {
        return servers;
    }

    public Map<String, ProxyServer> getProxys() {
        return proxys;
    }

    public Map<String, Quad<Integer, Integer, ServiceId, Template>> getWaitingServices() {
        return waitingServices;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public Wrapper getWrapper() {
        return this;
    }

    @Override
    public String getServerId() {
        return serverId;
    }

    public WrapperInfo getWrapperInfo() {
        return wrapperInfo;
    }

    public void setWrapperInfo(WrapperInfo wrapperInfo) {
        this.wrapperInfo = wrapperInfo;
    }

    public WrapperMeta getNetworkInfo() {
        return networkInfo;
    }

    @Override
    public String getName() {
        return serverId;
    }

    public int getUsedMemoryAndWaitings() {
        AtomicInteger integer = new AtomicInteger(getUsedMemory());

        CollectionWrapper.iterator(this.waitingServices.values(), new Runnabled<Quad<Integer, Integer, ServiceId, Template>>() {
            @Override
            public void run(Quad<Integer, Integer, ServiceId, Template> obj) {
                integer.addAndGet(obj.getSecond());
            }
        });

        return integer.get();
    }

    public int getUsedMemory() {
        int mem = 0;

        for (ProxyServer proxyServer : proxys.values()) {
            mem = mem + proxyServer.getProxyInfo().getMemory();
        }

        for (MinecraftServer proxyServer : servers.values()) {
            mem = mem + proxyServer.getProcessMeta().getMemory();
        }

        return mem;
    }

    public void sendCommand(String commandLine) {
        sendPacket(new PacketOutExecuteCommand(commandLine));
    }

    public void disconnct() {
        this.wrapperInfo = null;
        this.maxMemory = 0;
        for (MinecraftServer minecraftServer : servers.values()) {
            try {
                minecraftServer.disconnect();
            } catch (Exception ex) {

            }
        }

        for (CloudService cloudServer : cloudServers.values()) {
            try {
                cloudServer.disconnect();
            } catch (Exception ex) {

            }
        }

        for (ProxyServer minecraftServer : proxys.values()) {
            try {
                minecraftServer.disconnect();
            } catch (Exception ex) {

            }
        }

        waitingServices.clear();
        servers.clear();
        cloudServers.clear();
        proxys.clear();
    }

    public Wrapper updateWrapper() {

        if (getChannel() == null) {
            return this;
        }

        // java.util.Map<String, > groups = NetworkUtils.newConcurrentHashMap();

        // java.util.Map<String, > proxyGroups = NetworkUtils.newConcurrentHashMap();
        return this;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void writeCommand(String commandLine) {
        sendPacket(new PacketOutExecuteCommand(commandLine));
    }

    public void writeServerCommand(String commandLine, ServiceInfo serverInfo) {
        sendPacket(new PacketOutExecuteServerCommand(serverInfo, commandLine));
    }

    public void writeProxyCommand(String commandLine, ProxyInfo proxyInfo) {
        sendPacket(new PacketOutExecuteServerCommand(proxyInfo, commandLine));
    }

    public Collection<Integer> getBinndedPorts() {
        Collection<Integer> ports = new ArrayList<>();

        for (Quad<Integer, Integer, ServiceId, Template> serviceIdValues : waitingServices.values()) {
            ports.add(serviceIdValues.getFirst());
        }

        for (MinecraftServer minecraftServer : servers.values()) {
            ports.add(minecraftServer.getProcessMeta().getPort());
        }

        for (ProxyServer proxyServer : proxys.values()) {
            ports.add(proxyServer.getProcessMeta().getPort());
        }

        return ports;
    }

    public void startProxy(ProxyProcessMeta proxyProcessMeta) {
        sendPacket(new PacketOutStartProxy(proxyProcessMeta));
        System.out.println("Proxy [" + proxyProcessMeta.getServiceId() + "] is now in " + serverId + " queue.");

        this.waitingServices.put(proxyProcessMeta.getServiceId().getServerId(),
                new Quad<>(proxyProcessMeta.getPort(),
                        proxyProcessMeta.getMemory(),
                        proxyProcessMeta.getServiceId(),
                        null));
    }

    public void startProxyAsync(ProxyProcessMeta proxyProcessMeta) {
        sendPacket(new PacketOutStartProxy(proxyProcessMeta, true));
        System.out.println("Proxy [" + proxyProcessMeta.getServiceId() + "] is now in " + serverId + " queue.");

        this.waitingServices.put(proxyProcessMeta.getServiceId().getServerId(),
                new Quad<>(proxyProcessMeta.getPort(),
                        proxyProcessMeta.getMemory(),
                        proxyProcessMeta.getServiceId(),
                        null));
    }

    public void startGameServer(ServiceProcessMeta serverProcessMeta) {
        sendPacket(new PacketOutStartServer(serverProcessMeta));
        System.out.println("Service [" + serverProcessMeta.getServiceId() + "] is now in " + serverId + " queue.");

        this.waitingServices.put(serverProcessMeta.getServiceId().getServerId(),
                new Quad<>(serverProcessMeta.getPort(),
                        serverProcessMeta.getMemory(),
                        serverProcessMeta.getServiceId(),
                        serverProcessMeta.getTemplate()));
    }

    public void startGameServerAsync(ServiceProcessMeta serverProcessMeta) {
        sendPacket(new PacketOutStartServer(serverProcessMeta, true));
        System.out.println("Service [" + serverProcessMeta.getServiceId() + "] is now in " + serverId + " queue.");

        this.waitingServices.put(serverProcessMeta.getServiceId().getServerId(),
                new Quad<>(serverProcessMeta.getPort(),
                        serverProcessMeta.getMemory(),
                        serverProcessMeta.getServiceId(),
                        serverProcessMeta.getTemplate()));
    }

    public Wrapper stopServer(MinecraftServer minecraftServer) {
        if (this.servers.containsKey(minecraftServer.getServerId())) {
            sendPacket(new PacketOutStopServer(minecraftServer.getServerInfo()));
        }

        this.waitingServices.remove(minecraftServer.getServerId());
        return this;
    }

    public Wrapper stopServer(CloudService cloudServer) {
        if (this.servers.containsKey(cloudServer.getServerId())) {
            sendPacket(new PacketOutStopServer(cloudServer.getServerInfo()));
        }

        this.waitingServices.remove(cloudServer.getServerId());
        return this;
    }

    public Wrapper stopProxy(ProxyServer proxyServer) {
        if (this.proxys.containsKey(proxyServer.getServerId())) {
            sendPacket(new PacketOutStopProxy(proxyServer.getProxyInfo()));
        }

        this.waitingServices.remove(proxyServer.getServerId());
        return this;
    }

    public Wrapper enableScreen(ServiceInfo serverInfo) {
        sendPacket(new PacketOutScreen(serverInfo, DefaultType.BUKKIT, true));
        return this;
    }

    public Wrapper enableScreen(ProxyInfo serverInfo) {
        sendPacket(new PacketOutScreen(serverInfo, DefaultType.BUNGEE_CORD, true));
        return this;
    }

    public Wrapper disableScreen(ProxyInfo serverInfo) {
        sendPacket(new PacketOutScreen(serverInfo, DefaultType.BUNGEE_CORD, false));
        return this;
    }

    public Wrapper disableScreen(ServiceInfo serverInfo) {
        sendPacket(new PacketOutScreen(serverInfo, DefaultType.BUKKIT, false));
        return this;
    }

    public Wrapper copyServer(ServiceInfo serverInfo) {
        sendPacket(new PacketOutCopyServer(serverInfo));
        return this;
    }

    public Wrapper copyServer(ServiceInfo serverInfo, Template template) {
        sendPacket(new PacketOutCopyServer(serverInfo, template));
        return this;
    }

    /*public void setConfigProperties(// properties) {
        sendPacket(new PacketOutUpdateWrapperProperties(properties));
    }*/
}
