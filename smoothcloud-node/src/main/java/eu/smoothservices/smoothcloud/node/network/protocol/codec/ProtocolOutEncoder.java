package eu.smoothservices.smoothcloud.node.network.protocol.codec;

import eu.smoothservices.smoothcloud.node.network.protocol.*;
import io.netty.handler.codec.MessageToByteEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public final class ProtocolOutEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        ProtocolBuffer protocolBuffer = ProtocolProvider.protocolBuffer(byteBuf);

        if (o instanceof ProtocolRequest protocolRequest) {
            IProtocol protocol = ProtocolProvider.getProtocol(protocolRequest.getId());
            ProtocolStream protocolStream = protocol.createElement(protocolRequest.getElement());
            protocolStream.write(protocolBuffer);
        } else {
            for (IProtocol protocol : ProtocolProvider.protocols()) {
                ProtocolStream protocolStream = protocol.createElement(o);
                if (protocolStream != null) {
                    protocolStream.write(protocolBuffer);
                    break;
                }
            }
        }
    }
}
