package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.setup.CloudSetup;
import eu.smoothservices.smoothcloud.node.messages.SetupGroupMessages;
import eu.smoothservices.smoothcloud.node.messages.SetupMessages;
import eu.smoothservices.smoothcloud.node.setup.SetupGroup;
import lombok.Getter;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terminal {
    @Getter
    private PrintWriter writer;
    @Getter
    private BufferedReader reader;
    private final ExecutorService service;

    public Terminal() {
        this.writer = new PrintWriter(System.out, true);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.service = Executors.newCachedThreadPool();
        start();
    }

    private void start() {
        boolean isRunning = true;
        service.execute(() -> {
            try {
                while (isRunning) {
                    if (false/*!((SmoothCloudNode) SmoothCloudNode.getInstance()).getConfig().isLoaded()*/) {
                        if (SmoothCloudNode.isSettingUp) {
                            continue;
                        }
                        writer.append(JavaColor.apply(SetupMessages.PREFIX + SetupMessages.EULA_ACCEPT));
                        writer.flush();
                        writer.append("\n").append(JavaColor.apply(SetupMessages.PREFIX));
                        writer.flush();
                        new CloudSetup().setup();
                        continue;
                    }
                    if (SmoothCloudNode.isCreatingGroup) {
                        writer.append(JavaColor.apply(SetupGroupMessages.PREFIX + SetupGroupMessages.MEMORY));
                        writer.flush();
                        writer.append("\n").append(JavaColor.apply(SetupGroupMessages.PREFIX));
                        writer.flush();
                        new SetupGroup("name").setup();
                        continue;
                    }

                    writer.append(JavaColor.apply("&9Smooth&bCloud &8» &7"));
                    writer.flush();
                    String input = reader.readLine().trim();

                    if (((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider() != null && ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider().containsCommand(input)) {
                        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider().call(input.split(" "));
                    }
                }
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        });
    }

    public void shutdown() {
        service.shutdown();
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
