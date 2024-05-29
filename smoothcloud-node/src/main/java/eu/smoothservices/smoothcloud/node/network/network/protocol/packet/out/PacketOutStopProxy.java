package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyInfo;

public class PacketOutStopProxy extends Packet {

    public PacketOutStopProxy(ProxyInfo proxyInfo) {
        super(PacketRC.SERVER_HANDLE + 7, new Document("serverId", proxyInfo.getServiceId().getServerId()));
    }

}
