package eu.smoothservices.smoothcloud.node.terminal;

import java.util.concurrent.BlockingQueue;

public class ProcessThread extends Thread {

    private final BlockingQueue<String> queue;

    public ProcessThread(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String command = null;
            try {
                command = queue.take();
                processCommand(command);
            } catch (InterruptedException e) {
                continue;
            }

        }
    }

    private void processCommand(String command) {

    }
}
