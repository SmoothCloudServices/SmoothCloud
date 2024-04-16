package eu.smoothcloudservices.smoothcloud.node;

import eu.smoothcloudservices.smoothcloud.node.server.NettyServer;
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
