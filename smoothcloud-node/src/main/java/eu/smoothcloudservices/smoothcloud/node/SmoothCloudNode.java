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

import java.io.File;
import java.util.ArrayList;

@Getter
public final class SmoothCloudNode extends SmoothCloudAPI {
    private final JLine3Terminal terminal;
    private final CloudConfig config;
    private final CloudSetup setup;
    private CommandProvider commandProvider;

    private CloudGroupProvider groupProvider;
    private CloudServiceProvider serviceProvider;

    private NettyServer nettyServer;

    public SmoothCloudNode() {
        this.terminal = new JLine3Terminal();

        this.config = new CloudConfig();
        this.setup = new CloudSetup();

        String[] strings = new String[1];
        this.setup.setup(new String[1]);

        if (new File("config.cfg").length() == 0) { // todo: after complete start - temporary
            return;
        }

        this.commandProvider = new CommandProvider();

        this.groupProvider = new CloudGroupProviderImpl();
        this.serviceProvider = new CloudServiceProviderImpl();

        this.nettyServer = new NettyServer();

        Runtime.getRuntime().addShutdownHook(new Thread(SmoothCloudShutdownHandler::run));
        new CloudSetup();
    }
}
