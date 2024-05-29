package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;

public class PacketOutCustomChannelMessage extends Packet {

    public PacketOutCustomChannelMessage(String channel, String message, Document value) {
        super(PacketRC.SERVER_HANDLE + 3, new Document("message", message).append("value", value).append("channel", channel));
    }
}
