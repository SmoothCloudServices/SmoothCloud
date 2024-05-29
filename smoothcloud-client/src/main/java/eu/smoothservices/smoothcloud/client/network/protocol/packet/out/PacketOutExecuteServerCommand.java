package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;


import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;

public class PacketOutExecuteServerCommand extends Packet {
    public PacketOutExecuteServerCommand(ServiceInfo serverInfo, String commandLine) {
        super(PacketRC.SC_CORE + 7, new Document("serverInfo", serverInfo).append("type", DefaultType.BUKKIT)
                .append("commandLine", commandLine));
    }

    public PacketOutExecuteServerCommand(ProxyInfo serverInfo, String commandLine) {
        super(PacketRC.SC_CORE + 7, new Document("proxyInfo", serverInfo).append("type", DefaultType.BUNGEE_CORD)
                .append("commandLine", commandLine));
    }
}
