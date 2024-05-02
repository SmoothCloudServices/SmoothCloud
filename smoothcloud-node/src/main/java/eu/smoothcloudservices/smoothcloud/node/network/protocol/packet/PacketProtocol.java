package eu.smoothcloudservices.smoothcloud.node.network.protocol.packet;

import eu.smoothcloudservices.smoothcloud.node.network.protocol.IProtocol;
import eu.smoothcloudservices.smoothcloud.node.network.protocol.ProtocolStream;

import java.util.Arrays;
import java.util.Collection;

public class PacketProtocol implements IProtocol {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public Collection<Class<?>> getAvailableClasses() {
        return Arrays.asList(Packet.class);
    }

    @Override
    public ProtocolStream createElement(Object element) {
        if (element instanceof Packet) {
            return (Packet) element;
        }
        return null;
    }

    @Override
    public ProtocolStream createEmptyElement() {
        return new Packet();
    }
}
