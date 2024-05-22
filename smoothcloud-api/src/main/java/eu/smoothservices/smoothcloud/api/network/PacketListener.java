package eu.smoothservices.smoothcloud.api.network;

import eu.smoothservices.smoothcloud.api.network.impl.AuthPacket;

import java.util.HashMap;
import java.util.Map;

public final class PacketListener {
    private static Map<Integer, Class<?>> packetClassesById = new HashMap<>();
    private static Map<Class<?>, Integer> packetClassesByClass = new HashMap<>();

    static {
        registerPacket(AuthPacket.class, 0);
    }

    private static void registerPacket(Class<?> packet, Integer id) {
        packetClassesById.put(id, packet);
        packetClassesByClass.put(packet, id);
    }

    public static int getPacketId(Class<?> clazz) {
        return packetClassesByClass.get(clazz);
    }

    public static Class<?> getPacketClass(int id) {
        return packetClassesById.get(id);
    }

    public static boolean isPacketIdExists(int id) {
        return packetClassesById.containsKey(id);
    }
}
