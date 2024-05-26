package eu.smoothservices.smoothcloud.api.network.impl;

import eu.smoothservices.smoothcloud.api.network.IPacket;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthPacket implements IPacket {

    @Override
    public void write(ByteBuf buffer) {

    }

    @Override
    public void read(ByteBuf buffer) {

    }
}
