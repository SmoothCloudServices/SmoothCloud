package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.service.ServiceProcessMeta;

public class PacketOutStartServer extends Packet {

    public PacketOutStartServer(ServiceProcessMeta serverProcessMeta) {
        super(PacketRC.SC_CORE + 3, new Document("serverProcess", serverProcessMeta));
    }

    public PacketOutStartServer(ServiceProcessMeta serverProcessMeta, boolean async) {
        super(PacketRC.SC_CORE + 3, new Document("serverProcess", serverProcessMeta).append("async", async));
    }
}