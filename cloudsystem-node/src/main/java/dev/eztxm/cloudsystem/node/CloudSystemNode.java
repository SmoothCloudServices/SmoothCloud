package dev.eztxm.cloudsystem.node;

import dev.eztxm.cloudsystem.api.CloudSystemAPI;
import dev.eztxm.cloudsystem.api.group.CloudGroupProvider;
import dev.eztxm.cloudsystem.api.service.CloudServiceProvider;
import dev.eztxm.cloudsystem.node.command.CommandProvider;
import dev.eztxm.cloudsystem.node.config.CloudConfig;
import dev.eztxm.cloudsystem.node.group.CloudGroupProviderImpl;
import dev.eztxm.cloudsystem.node.server.NettyServer;
import dev.eztxm.cloudsystem.node.service.CloudServiceProviderImpl;
import dev.eztxm.cloudsystem.node.setup.CloudSetup;
import dev.eztxm.cloudsystem.node.terminal.JLine3Terminal;
import lombok.Getter;

@Getter
public final class CloudSystemNode extends CloudSystemAPI {
    private final JLine3Terminal terminal;
    private final CloudConfig config;
    private final CommandProvider commandProvider;

    private final CloudGroupProvider groupProvider;
    private final CloudServiceProvider serviceProvider;

    private final NettyServer nettyServer;

    public CloudSystemNode() {
        this.terminal = new JLine3Terminal();

        this.config = new CloudConfig();
        new CloudSetup(config);

        this.commandProvider = new CommandProvider();

        this.groupProvider = new CloudGroupProviderImpl();
        this.serviceProvider = new CloudServiceProviderImpl();

        this.nettyServer = new NettyServer();

        Runtime.getRuntime().addShutdownHook(new Thread(CloudSystemShutdownHandler::run));
    }
}
