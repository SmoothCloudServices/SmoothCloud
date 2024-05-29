package eu.smoothservices.smoothcloud.client.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.client.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.lib.DefaultType;

public class PacketOutCustomSubChannelMessage extends Packet {

    public PacketOutCustomSubChannelMessage(DefaultType defaultType, String channel, String message, Document value) {
        super(PacketRC.SERVER_HANDLE + 8, new Document("defaultType", defaultType).append("channel", channel)
                .append("message", message)
                .append("value", value));
    }

    public PacketOutCustomSubChannelMessage(DefaultType defaultType, String serverId, String channel, String message, Document value) {
        super(PacketRC.SERVER_HANDLE + 8, new Document("defaultType", defaultType).append("serverId", serverId)
                .append("channel", channel)
                .append("message", message)
                .append("value", value));
    }

}
