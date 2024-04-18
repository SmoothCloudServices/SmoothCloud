package eu.smoothcloudservices.smoothcloud.node.terminal;

import eu.smoothcloudservices.smoothcloud.api.util.ThreadSafe;
import lombok.Getter;

import java.io.IOException;

@Getter
public class TerminalManager {
    private final Terminal terminal;
    private boolean input = true;

    public TerminalManager() {
        this.terminal = new Terminal();
    }

    public void shutdown() {
        terminal.shutdown();
    }

    public void closeInput() {
        input = false;
    }

    public void startInput() {
        input = true;
    }

    public void closeAppend(String text) {
        if(input) {
            closeInput();
        }
        terminal.getWriter().append(JavaColor.apply(text));
        terminal.getWriter().flush();
    }

    public void openAppend(String text) {
        if(!input) {
            startInput();
        }
        terminal.getWriter().append(JavaColor.apply(text));
        terminal.getWriter().flush();
    }

    public void append(String text) {
        terminal.getWriter().append(JavaColor.apply(text));
        terminal.getWriter().flush();
        if(input) {
            terminal.getWriter().append("\n");
            terminal.getWriter().append(JavaColor.apply("&9Smooth&bCloud &8» &7"));
        }
    }

    public void closeAppend(String prefix, String text) {
        if(input) {
            closeInput();
        }
        terminal.getWriter().append(JavaColor.apply(prefix)).append(JavaColor.apply(text)).append("\n");
        terminal.getWriter().flush();
    }

    public void userAppend(String prefix) {
        if(!input) {
            startInput();
        }
        terminal.getWriter().append(JavaColor.apply(prefix));
        terminal.getWriter().flush();
    }

    public void openAppend(String prefix, String text) {
        if(!input) {
            startInput();
        }
        terminal.getWriter().append(JavaColor.apply(prefix)).append(JavaColor.apply(text));
        terminal.getWriter().flush();
        terminal.getWriter().append("\n");
        terminal.getWriter().append(JavaColor.apply(prefix));
        terminal.getWriter().flush();
    }

    public void append(String prefix, String text) {
        terminal.getWriter().append(JavaColor.apply(prefix)).append(JavaColor.apply(text));
        terminal.getWriter().flush();
        if(input) {
            terminal.getWriter().append("\n");
            terminal.getWriter().append(JavaColor.apply(prefix));
        }
    }

    public ThreadSafe<Void> clearScreen() {
        return ThreadSafe.run(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println();
            }
        });
    }

    public String read() {
        try {
            return terminal.getReader().readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}