package eu.smoothservices.smoothcloud.api.network.codec;

import eu.smoothservices.smoothcloud.api.network.IPacket;
import eu.smoothservices.smoothcloud.api.network.PacketListener;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class PacketEncoder extends MessageToByteEncoder<IPacket> {

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, IPacket msg, boolean preferDirect) throws Exception {
        return ctx.alloc().buffer(0);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IPacket msg, ByteBuf out) throws Exception {
        out.writeInt(PacketListener.getPacketId(msg.getClass()));
        msg.write(out);
    }
}
