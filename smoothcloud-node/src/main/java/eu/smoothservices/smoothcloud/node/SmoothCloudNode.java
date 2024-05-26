package eu.smoothservices.smoothcloud.node;

import com.github.lalyos.jfiglet.FigletFont;
import eu.smoothservices.smoothcloud.api.SmoothCloudAPI;
import eu.smoothservices.smoothcloud.api.group.ICloudGroupProvider;
import eu.smoothservices.smoothcloud.api.player.ICloudPlayerProvider;
import eu.smoothservices.smoothcloud.api.service.ICloudServiceProvider;
import eu.smoothservices.smoothcloud.node.command.CommandProvider;
import eu.smoothservices.smoothcloud.node.config.MainConfig;
import eu.smoothservices.smoothcloud.node.group.ICloudGroupProviderImpl;
import eu.smoothservices.smoothcloud.node.player.CloudPlayerProviderImpl;
import eu.smoothservices.smoothcloud.node.server.NettyServer;
import eu.smoothservices.smoothcloud.node.service.CloudServiceProviderImpl;
import eu.smoothservices.smoothcloud.node.terminal.JavaColor;
import eu.smoothservices.smoothcloud.node.terminal.TerminalManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

@Getter
public final class SmoothCloudNode extends SmoothCloudAPI {

    public static boolean isSettingUp = false;
    public static boolean isCreatingGroup = false;
    public static String path = "E:/Desktop/SCS - Testing";

    private final MainConfig config;
    private final TerminalManager terminalManager;
    private CommandProvider commandProvider;

    private ICloudGroupProvider groupProvider;
    private ICloudServiceProvider serviceProvider;
    private ICloudPlayerProvider playerProvider;

    private NettyServer nettyServer;

    @SneakyThrows
    public SmoothCloudNode() {
        File configFile = new File(path, "config.yml");

        this.config = new MainConfig(configFile);

        this.terminalManager = new TerminalManager();

        if(!isSettingUp /*&& this.config.getHost() != null*/) {
            startCloud();
            return;
        }

        // Start setup terminal
    }

    @SneakyThrows
    public void startCloud() {
        this.terminalManager.getTerminal().writeCleanLine(JavaColor.apply(STR."\n&b\{FigletFont.convertOneLine("SmoothCloud")}"));

        this.terminalManager.start();

        this.terminalManager.getTerminal().writeLine("Starting CommandProvider...");
        this.commandProvider = new CommandProvider();
        this.terminalManager.getTerminal().writeLine("CommandProvider started.");

        this.terminalManager.getTerminal().writeLine("Starting CloudGroupProvider...");
        this.groupProvider = new ICloudGroupProviderImpl();
        this.terminalManager.getTerminal().writeLine("CloudGroupProvider started.");

        this.terminalManager.getTerminal().writeLine("Starting CloudServiceProvider...");
        this.serviceProvider = new CloudServiceProviderImpl();
        this.terminalManager.getTerminal().writeLine("CloudServiceProvider started.");

        this.terminalManager.getTerminal().writeLine("Starting CloudPlayerProvider...");
        this.playerProvider = new CloudPlayerProviderImpl();
        this.terminalManager.getTerminal().writeLine("CloudPlayerProvider started.");

        this.terminalManager.getTerminal().writeLine("Starting Connection for the wrapper...");
        //this.nettyServer = new NettyServer();
        this.terminalManager.getTerminal().writeLine("Connection for the wrapper started.");

        this.terminalManager.getTerminal().writeLine("Starting Internal Wrapper...");
        //this.wrapper = new SmoothCloudWrapper(config.getAddress().getHostName(), config.getAddress().getHostPort());
        this.terminalManager.getTerminal().writeLine("Internal Wrapper started.");

        Runtime.getRuntime().addShutdownHook(new Thread(SmoothCloudShutdownHandler::run));
    }
}
