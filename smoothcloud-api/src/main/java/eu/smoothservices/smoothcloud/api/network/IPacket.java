package eu.smoothservices.smoothcloud.api.network;

import io.netty.buffer.ByteBuf;

public interface IPacket {

    void write(ByteBuf buffer);
    void read(ByteBuf buffer);

}
