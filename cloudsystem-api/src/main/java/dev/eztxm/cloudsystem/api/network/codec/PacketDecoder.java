package dev.eztxm.cloudsystem.api.network.codec;

import dev.eztxm.cloudsystem.api.network.Packet;
import dev.eztxm.cloudsystem.api.network.PacketListener;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.ByteToMessageDecoder;
import lombok.val;

public final class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, Buffer in) throws Exception {
        var id = in.readInt();

        if (!PacketListener.isPacketIdExists(id)) {
            throw new IllegalStateException(STR."Packet with Id \{id} not found");
        }

        val packet = (Packet) PacketListener.getPacketClass(id).getConstructor().newInstance();
        packet.read(in);

        ctx.fireChannelRead(packet);
    }
}
