package eu.smoothcloudservices.smoothcloud.node.network.protocol.codec;

import eu.smoothcloudservices.smoothcloud.node.network.protocol.ProtocolBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ProtocolLengthDeserializer extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        in.markReaderIndex();
        byte[] lengthBytes = new byte[3];

        for (int i = 0; i < 3; i++) {
            if (!in.isReadable()) {
                in.resetReaderIndex();
                return;
            }

            lengthBytes[i] = in.readByte();

            if (lengthBytes[i] >= 0) {
                ProtocolBuffer buffer = new ProtocolBuffer(Unpooled.wrappedBuffer(lengthBytes));

                try {
                    int packetLength = buffer.readVarInt();

                    if (in.readableBytes() < packetLength) {
                        in.resetReaderIndex();
                        return;
                    }

                    out.add(in.readBytes(packetLength));
                } finally {
                    buffer.release();
                }

                return;
            }
        }
    }

}
