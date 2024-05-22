package eu.smoothservices.smoothcloud.node.terminal;

import eu.smoothcloudservices.smoothcloud.node.command.CommandResolver;
import lombok.SneakyThrows;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Terminal2 {

    private final int QUEUE_CAPACITY = 10;
    private final BlockingQueue<String> queue;
    private final Thread processor;
    private final Scanner scanner;
    private boolean terminated = false;
    private CommandResolver resolver;

    @SneakyThrows
    public Terminal2() {
        queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        processor = new ProcessThread(queue);
        processor.start();
        resolver = new CommandResolver();
        scanner = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        while(!isTerminated()) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                boolean isCommand = resolver.resolveCommand(input);
                if(!isCommand) continue;

            }
        }

    }

    private void shutdown() {
        terminated = true;
        scanner.close();
        processor.interrupt();
        try {
            processor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(STR."Interrupted during shutdown: \{e.getMessage()}");
        }

    }

    public boolean isTerminated() {
        return terminated;
    }
}
