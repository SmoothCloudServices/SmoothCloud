package eu.smoothservices.smoothcloud.api.network.impl;

import eu.smoothservices.smoothcloud.api.network.IPacket;
import io.netty5.buffer.Buffer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthPacket implements IPacket {

    @Override
    public void write(Buffer buffer) {

    }

    @Override
    public void read(Buffer buffer) {

    }
}
