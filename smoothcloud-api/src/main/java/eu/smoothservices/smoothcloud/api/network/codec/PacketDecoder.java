package eu.smoothservices.smoothcloud.api.network.codec;

import eu.smoothservices.smoothcloud.api.network.IPacket;
import eu.smoothservices.smoothcloud.api.network.PacketListener;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.val;

import java.util.List;

public final class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        var id = in.readInt();

        if (!PacketListener.isPacketIdExists(id)) {
            throw new IllegalStateException(STR."Packet with Id \{id} not found");
        }

        val packet = (IPacket) PacketListener.getPacketClass(id).getConstructor().newInstance();
        packet.read(in);

        ctx.fireChannelRead(packet);
    }
}
