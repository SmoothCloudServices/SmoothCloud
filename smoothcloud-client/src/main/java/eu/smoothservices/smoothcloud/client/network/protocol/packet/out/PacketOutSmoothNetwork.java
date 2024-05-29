package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.client.lib.SmoothNetwork;
import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;

public class PacketOutSmoothNetwork extends Packet {

    public PacketOutSmoothNetwork(SmoothNetwork smoothNetwork) {
        super(PacketRC.SERVER_HANDLE + 1, new Document("smoothnetwork", smoothNetwork));
    }

}
