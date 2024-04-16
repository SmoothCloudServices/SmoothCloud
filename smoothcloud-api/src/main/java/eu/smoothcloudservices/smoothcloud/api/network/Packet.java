package eu.smoothcloudservices.smoothcloud.api.network;

import io.netty5.buffer.Buffer;

public interface Packet {

    void write(Buffer buffer);
    void read(Buffer buffer);

}
