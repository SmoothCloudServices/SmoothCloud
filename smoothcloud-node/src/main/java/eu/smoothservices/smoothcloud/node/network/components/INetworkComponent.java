package eu.smoothservices.smoothcloud.node.network.components;

import eu.smoothservices.smoothcloud.node.network.protocol.IProtocol;
import eu.smoothservices.smoothcloud.node.network.protocol.ProtocolRequest;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketSender;
import eu.smoothservices.smoothcloud.node.util.interfaces.ChannelUser;
import io.netty.channel.ChannelFutureListener;

public interface INetworkComponent extends PacketSender, ChannelUser {

    Wrapper getWrapper();

    default void sendPacket(Packet... packets) {
        for(Packet packet : packets) {
            sendPacket(packet);
        }
    }

    String getServerId();

    default void sendPacket(Packet packet) {
        if(getChannel() == null) return;
        if(getChannel().eventLoop().inEventLoop()) {
            try {
                getChannel().writeAndFlush(packet).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            } catch (Exception ignored) {}
            return;
        }
        getChannel().eventLoop().execute(() -> {
            try {
                getChannel().writeAndFlush(packet).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            } catch (Exception ignored) {}
        });
    }

    default void sendPacketSynchronized(Packet packet) {
        if(getChannel() == null) return;
        getChannel().writeAndFlush(packet).syncUninterruptibly();
    }

    @Override
    default void send(Object object) {
        if(getChannel() == null) return;

        if(getChannel().eventLoop().inEventLoop()) {
            getChannel().writeAndFlush(object);
            return;
        }
        getChannel().eventLoop().execute(() -> getChannel().writeAndFlush(object));
    }

    @Override
    default void sendSynchronized(Object object) {
        getChannel().writeAndFlush(object).syncUninterruptibly();
    }

    @Override
    default void sendAsynchronized(Object object) {
        getChannel().writeAndFlush(object);
    }

    @Override
    default void send(IProtocol iProtocol, Object element) {
        send(new ProtocolRequest(iProtocol.getId(), element));
    }

    @Override
    default void send(int id, Object element) {
        send(new ProtocolRequest(id, element));
    }

    @Override
    default void sendAsynchronized(int id, Object element) {
        sendAsynchronized(new ProtocolRequest(id, element));
    }

    @Override
    default void sendAsynchronized(IProtocol iProtocol, Object element) {
        sendSynchronized(new ProtocolRequest(iProtocol.getId(), element));
    }

    @Override
    default void sendSynchronized(int id, Object element) {
        sendSynchronized(new ProtocolRequest(id, element));
    }

    @Override
    default void sendSynchronized(IProtocol iProtocol, Object element) {
        sendSynchronized(new ProtocolRequest(iProtocol.getId(), element));
    }

}
