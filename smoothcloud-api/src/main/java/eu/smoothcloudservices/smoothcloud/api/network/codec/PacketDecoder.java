package eu.smoothcloudservices.smoothcloud.api.network.codec;

import eu.smoothcloudservices.smoothcloud.api.network.IPacket;
import eu.smoothcloudservices.smoothcloud.api.network.PacketListener;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.ByteToMessageDecoder;
import lombok.val;

import static java.lang.StringTemplate.STR;

public final class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, Buffer in) throws Exception {
        var id = in.readInt();

        if (!PacketListener.isPacketIdExists(id)) {
            throw new IllegalStateException(STR."Packet with Id \{id} not found");
        }

        val packet = (IPacket) PacketListener.getPacketClass(id).getConstructor().newInstance();
        packet.read(in);

        ctx.fireChannelRead(packet);
    }
}
