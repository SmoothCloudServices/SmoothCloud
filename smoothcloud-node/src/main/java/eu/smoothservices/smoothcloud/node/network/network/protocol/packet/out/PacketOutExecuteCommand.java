package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;

public class PacketOutExecuteCommand extends Packet {

    public PacketOutExecuteCommand(String commandLine) {
        super(PacketRC.SC_CORE + 4, new Document("command", commandLine));
    }
}
