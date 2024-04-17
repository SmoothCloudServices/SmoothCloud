package eu.smoothcloudservices.smoothcloud.node.terminal;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.command.CommandProvider;
import lombok.Getter;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terminal {

    @Getter
    private PrintWriter writer;
    @Getter
    private BufferedReader reader;
    private ExecutorService service;

    public Terminal() {
        this.writer = new PrintWriter(System.out, true);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.service = Executors.newCachedThreadPool();
        start();
    }

    private void start() {

        service.execute(() -> {
            try {
                while(true) {
                    writer.append(Color.translate("&0CloudSystem &2» &1"));
                    writer.flush();
                    String input = reader.readLine().trim();

                    if( ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider() != null && ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider().containsCommand(input)) {
                        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider().call(input.split(" "));
                        continue;
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
