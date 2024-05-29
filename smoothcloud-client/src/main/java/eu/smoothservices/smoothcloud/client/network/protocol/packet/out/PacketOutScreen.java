package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;

public class PacketOutScreen extends Packet {

    public PacketOutScreen(ServiceInfo serverInfo, DefaultType type, boolean enable) {
        super(PacketRC.SC_CORE + 6, new Document("serverInfo", serverInfo).append("type", type).append("enable", enable));
    }

    public PacketOutScreen(ProxyInfo serverInfo, DefaultType type, boolean enable) {
        super(PacketRC.SC_CORE + 6, new Document("proxyInfo", serverInfo).append("type", type).append("enable", enable));
    }
}
