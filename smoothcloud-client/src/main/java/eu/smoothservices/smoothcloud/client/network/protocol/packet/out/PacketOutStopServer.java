package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;

public class PacketOutStopServer extends Packet {

    public PacketOutStopServer(String serverId) {
        super(PacketRC.SERVER_HANDLE + 5, new Document("serverId", serverId));
    }
}
