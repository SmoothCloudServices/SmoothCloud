package eu.smoothservices.smoothcloud.node;

import com.github.lalyos.jfiglet.FigletFont;
import eu.smoothservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.api.group.ICloudGroupProvider;
import eu.smoothservices.smoothcloud.api.player.ICloudPlayerProvider;
import eu.smoothservices.smoothcloud.api.service.ICloudServiceProvider;
import eu.smoothservices.smoothcloud.node.command.CommandProvider;
import eu.smoothservices.smoothcloud.node.config.MainConfig;
import eu.smoothservices.smoothcloud.node.group.CloudGroupProviderImpl;
import eu.smoothservices.smoothcloud.node.player.CloudPlayerProviderImpl;
import eu.smoothservices.smoothcloud.node.server.NettyServer;
import eu.smoothservices.smoothcloud.node.service.CloudServiceProviderImpl;
import eu.smoothservices.smoothcloud.node.terminal.JavaColor;
import eu.smoothservices.smoothcloud.node.terminal.TerminalManager;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashMap;

@Getter
public final class SmoothCloudNode extends SmoothCloudAPI {
    public static boolean needSetup = false;
    public static boolean isCreatingGroup = false;
    public static String path = "E:/Desktop/SCS - Testing";

    private static HashMap<ServiceId, ICloudGroup> groups;

    private final MainConfig config;
    private final TerminalManager terminalManager;
    private CommandProvider commandProvider;

    private ICloudGroupProvider groupProvider;
    private ICloudServiceProvider serviceProvider;
    private ICloudPlayerProvider playerProvider;

    private NettyServer nettyServer;

    @SneakyThrows
    public SmoothCloudNode() {
        groups = new HashMap<>();
        this.config = new MainConfig(path, "config.json");
        this.terminalManager = new TerminalManager();
        if(this.config.getHost() != null && this.config.getMemory() != null) {
            this.terminalManager.start();
            startCloud();
            return;
        }
        needSetup = true;
        this.terminalManager.getCloudTerminal().writeCleanLine(JavaColor.apply(STR."\n&b\{FigletFont.convertOneLine("SmoothCloud  Setup")}"));
        this.terminalManager.start();
    }

    @SneakyThrows
    public void startCloud() {
        this.terminalManager.getCloudTerminal().writeCleanLine(JavaColor.apply(STR."\n&b\{FigletFont.convertOneLine("SmoothCloud")}"));

        Thread.sleep(1000);

        this.terminalManager.getCloudTerminal().writeLine("Starting CommandProvider...");
        this.commandProvider = new CommandProvider();
        this.terminalManager.getCloudTerminal().writeLine("CommandProvider started.");

        this.terminalManager.getCloudTerminal().writeLine("Starting CloudGroupProvider...");
        this.groupProvider = new CloudGroupProviderImpl();
        this.terminalManager.getCloudTerminal().writeLine("CloudGroupProvider started.");

        this.terminalManager.getCloudTerminal().writeLine("Starting CloudServiceProvider...");
        this.serviceProvider = new CloudServiceProviderImpl();
        this.terminalManager.getCloudTerminal().writeLine("CloudServiceProvider started.");

        this.terminalManager.getCloudTerminal().writeLine("Starting CloudPlayerProvider...");
        this.playerProvider = new CloudPlayerProviderImpl();
        this.terminalManager.getCloudTerminal().writeLine("CloudPlayerProvider started.");

        this.terminalManager.getCloudTerminal().writeLine("Starting Connection for the wrapper...");
        this.nettyServer = new NettyServer();
        this.terminalManager.getCloudTerminal().writeLine("Connection for the wrapper started.");

        this.terminalManager.getCloudTerminal().writeLine("Starting Internal Wrapper...");
        //this.wrapper = new SmoothCloudWrapper(config.getAddress().getHostName(), config.getAddress().getHostPort());
        this.terminalManager.getCloudTerminal().writeLine("Internal Wrapper started.");

        this.terminalManager.getCloudTerminal().userInput();

        Runtime.getRuntime().addShutdownHook(new Thread(SmoothCloudShutdownHandler::run));
    }

    /***
     * This method is completely useless.
     * <p>
     * Use the getGroup method from <b>ICloudGroupProviderImpl</b> instead to make a new method.
     * <p>
     * Example: <b>SmoothCloudNode.getInstance().getGroupProvider().getGroup("name");</b>
     */
    @Deprecated(forRemoval = true)
    public static ICloudGroup getGroup(ServiceId serviceId) {
        return groups.get(serviceId);
    }
}
