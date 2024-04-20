package eu.smoothcloudservices.smoothcloud.api.network.codec;

import eu.smoothcloudservices.smoothcloud.api.network.IPacket;
import eu.smoothcloudservices.smoothcloud.api.network.PacketListener;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToByteEncoder;

public final class PacketEncoder extends MessageToByteEncoder<IPacket> {

    @Override
    protected Buffer allocateBuffer(ChannelHandlerContext ctx, IPacket msg) {
        return ctx.bufferAllocator().allocate(0);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IPacket msg, Buffer out) {
        out.writeInt(PacketListener.getPacketId(msg.getClass()));
        msg.write(out);
    }
}
