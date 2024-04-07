package dev.eztxm.cloudsystem.wrapper.network;

import lombok.SneakyThrows;
import org.junit.Test;

public class ConnectionTest {

    @SneakyThrows
    @Test
    public void handle() {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("localhost", 8850);

        Thread.sleep(50000);
    }
}
