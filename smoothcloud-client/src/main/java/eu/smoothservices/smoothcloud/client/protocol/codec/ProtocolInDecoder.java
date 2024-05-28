package eu.smoothservices.smoothcloud.client.protocol.codec;

import eu.smoothservices.smoothcloud.client.protocol.ProtocolBuffer;
import eu.smoothservices.smoothcloud.client.protocol.ProtocolProvider;
import eu.smoothservices.smoothcloud.client.protocol.ProtocolStream;
import eu.smoothservices.smoothcloud.client.protocol.IProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ProtocolInDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        ProtocolBuffer protocolBuffer = ProtocolProvider.protocolBuffer(byteBuf);

        for (IProtocol iProtocol : ProtocolProvider.protocols()) {
            try {
                ProtocolStream protocolStream = iProtocol.createEmptyElement();
                protocolStream.read(protocolBuffer.clone());
                list.add(protocolStream);
                break;
            } catch (Exception ex) {

            }
        }
    }
}
