package eu.smoothcloudservices.smoothcloud.node;

import com.github.lalyos.jfiglet.FigletFont;
import eu.smoothcloudservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothcloudservices.smoothcloud.api.group.CloudGroupProvider;
import eu.smoothcloudservices.smoothcloud.api.service.CloudServiceProvider;
import eu.smoothcloudservices.smoothcloud.node.command.CommandProvider;
import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import eu.smoothcloudservices.smoothcloud.node.group.CloudGroupProviderImpl;
import eu.smoothcloudservices.smoothcloud.node.server.NettyServer;
import eu.smoothcloudservices.smoothcloud.node.service.CloudServiceProviderImpl;
import eu.smoothcloudservices.smoothcloud.node.terminal.JavaColor;
import eu.smoothcloudservices.smoothcloud.node.terminal.TerminalManager;
import eu.smoothcloudservices.smoothcloud.wrapper.SmoothCloudWrapper;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

@Getter
public final class SmoothCloudNode extends SmoothCloudAPI {

    public static boolean isSettingUp = false;
    public static final String PREFIX = "&9Smooth&bCloud &8» &7";

    private final TerminalManager terminal;
    private final CloudConfig config;
    private CommandProvider commandProvider;

    private CloudGroupProvider groupProvider;
    private CloudServiceProvider serviceProvider;

    private NettyServer nettyServer;

    private SmoothCloudWrapper wrapper;

    @SneakyThrows
    public SmoothCloudNode() {
        File configFile = new File("E:/Desktop/SCS - Testing", "config.cfg");

        this.terminal = new TerminalManager();

        this.config = new CloudConfig(configFile);
        this.config.load();

        if(configFile.length() == 0) {
            return;
        }

        if(!isSettingUp) {
            startCloud();
        }
    }

    @SneakyThrows
    public void startCloud() {
        this.terminal.closeAppend("\n");
        this.terminal.closeAppend(JavaColor.apply(STR."&b\{FigletFont.convertOneLine("SmoothCloud")}"));
        this.terminal.closeAppend("\n");

        this.terminal.closeAppend(PREFIX, "Starting CommandProvider...");
        this.commandProvider = new CommandProvider();
        this.terminal.closeAppend(PREFIX, "CommandProvider started.");

        this.terminal.closeAppend(PREFIX, "Starting CloudGroupProvider...");
        this.groupProvider = new CloudGroupProviderImpl();
        this.terminal.closeAppend(PREFIX, "CloudGroupProvider started.");
        this.terminal.closeAppend(PREFIX, "Starting CloudServiceProvider...");
        this.serviceProvider = new CloudServiceProviderImpl();
        this.terminal.closeAppend(PREFIX, "CloudServiceProvider started.");

        this.terminal.closeAppend(PREFIX, "Starting Connection for the wrapper...");
        this.nettyServer = new NettyServer();
        this.terminal.closeAppend(PREFIX, "Connection for the wrapper started.");

        this.terminal.closeAppend(PREFIX, "Starting Internal Wrapper...");
        this.wrapper = new SmoothCloudWrapper(config.getAddress().getHostName(), config.getAddress().getHostPort());
        this.terminal.closeAppend(PREFIX, "Internal Wrapper started.");

        this.terminal.closeAppend("\n");
        this.terminal.userAppend(PREFIX);

        Runtime.getRuntime().addShutdownHook(new Thread(SmoothCloudShutdownHandler::run));
    }
}
