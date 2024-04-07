package dev.eztxm.cloudsystem.node;

import dev.eztxm.cloudsystem.node.server.NettyServer;
import lombok.SneakyThrows;
import org.junit.Test;

public class ServerConnectionTest {

    @SneakyThrows
    @Test
    public void handle() {
        new NettyServer();
        Thread.sleep(1000000);
    }
}
