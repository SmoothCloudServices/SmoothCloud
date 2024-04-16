package eu.smoothcloudservices.smoothcloud.node;

import eu.smoothcloudservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothcloudservices.smoothcloud.api.group.CloudGroupProvider;
import eu.smoothcloudservices.smoothcloud.api.service.CloudServiceProvider;
import eu.smoothcloudservices.smoothcloud.node.command.CommandProvider;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.group.CloudGroupProviderImpl;
import eu.smoothcloudservices.smoothcloud.node.server.NettyServer;
import eu.smoothcloudservices.smoothcloud.node.service.CloudServiceProviderImpl;
import eu.smoothcloudservices.smoothcloud.node.setup.CloudSetup;
import eu.smoothcloudservices.smoothcloud.node.terminal.JLine3Terminal;
import lombok.Getter;

@Getter
public final class SmoothSmoothCloudNode extends SmoothCloudAPI {
    private final JLine3Terminal terminal;
    private final CloudConfig config;
    private final CommandProvider commandProvider;

    private final CloudGroupProvider groupProvider;
    private final CloudServiceProvider serviceProvider;

    private final NettyServer nettyServer;

    public SmoothSmoothCloudNode() {
        this.terminal = new JLine3Terminal();

        this.config = new CloudConfig();
        new CloudSetup(config);

        this.commandProvider = new CommandProvider();

        this.groupProvider = new CloudGroupProviderImpl();
        this.serviceProvider = new CloudServiceProviderImpl();

        this.nettyServer = new NettyServer();

        Runtime.getRuntime().addShutdownHook(new Thread(SmoothCloudShutdownHandler::run));
    }
}
