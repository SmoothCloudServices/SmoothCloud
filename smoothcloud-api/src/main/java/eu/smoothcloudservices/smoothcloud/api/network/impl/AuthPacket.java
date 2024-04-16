package eu.smoothcloudservices.smoothcloud.api.network.impl;

import eu.smoothcloudservices.smoothcloud.api.network.Packet;
import io.netty5.buffer.Buffer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthPacket implements Packet {

    @Override
    public void write(Buffer buffer) {

    }

    @Override
    public void read(Buffer buffer) {

    }
}
