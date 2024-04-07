package dev.eztxm.cloudsystem.api.network.impl;

import dev.eztxm.cloudsystem.api.network.Packet;
import io.netty5.buffer.Buffer;
import lombok.AllArgsConstructor;
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
