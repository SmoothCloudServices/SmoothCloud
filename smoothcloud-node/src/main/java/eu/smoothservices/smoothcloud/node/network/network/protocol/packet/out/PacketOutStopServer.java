package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;

public class PacketOutStopServer extends Packet {

    public PacketOutStopServer(String serverId) {
        super(PacketRC.SERVER_HANDLE + 5, new Document("serverId", serverId));
    }
}
