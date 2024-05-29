package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.service.info.ServiceInfo;
import eu.smoothservices.smoothcloud.node.util.service.template.Template;

public class PacketOutCopyServer extends Packet {

    public PacketOutCopyServer(ServiceInfo serverInfo) {
        super(PacketRC.SC_CORE + 10, new Document("serverInfo", serverInfo));
    }

    public PacketOutCopyServer(ServiceInfo serverInfo, Template template) {
        super(PacketRC.SC_CORE + 10, new Document("serverInfo", serverInfo).append("template", template));
    }
}
