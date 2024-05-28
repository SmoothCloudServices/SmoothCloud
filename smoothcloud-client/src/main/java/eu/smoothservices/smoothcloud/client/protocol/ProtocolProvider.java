package eu.smoothservices.smoothcloud.client.protocol;

import eu.smoothservices.smoothcloud.client.network.NetworkUtils;
import eu.smoothservices.smoothcloud.client.protocol.file.FileProtocol;
import eu.smoothservices.smoothcloud.client.protocol.packet.PacketProtocol;
import io.netty.buffer.ByteBuf;

import java.util.Collection;
import java.util.Map;

public class ProtocolProvider {

    private static Map<Integer, IProtocol> protocols;

    static {
        protocols = NetworkUtils.newConcurrentHashMap();
        registerProtocol(new PacketProtocol());
        registerProtocol(new FileProtocol());
    }

    private ProtocolProvider() {
    }

    public static ProtocolBuffer protocolBuffer(ByteBuf byteBuf) {
        return new ProtocolBuffer(byteBuf);
    }

    public static void registerProtocol(IProtocol iProtocol) {
        protocols.put(iProtocol.getId(), iProtocol);
    }

    public static IProtocol getProtocol(int id) {
        return protocols.get(id);
    }

    public static Collection<IProtocol> protocols() {
        return protocols.values();
    }
}
