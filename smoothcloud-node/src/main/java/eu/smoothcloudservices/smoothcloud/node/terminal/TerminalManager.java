package eu.smoothcloudservices.smoothcloud.node.terminal;

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
        terminal.getWriter().append(Color.translate("&0CloudSystem &2» &1")).append(Color.translate(text));
        terminal.getWriter().flush();
    }

    public void openAppend(String text) {
        if(!input) {
            startInput();
        }
        terminal.getWriter().append(Color.translate("&0CloudSystem &2» &1")).append(Color.translate(text));
        terminal.getWriter().flush();
    }

    public void append(String text) {
        terminal.getWriter().append(Color.translate("&0CloudSystem &2» &1")).append(Color.translate(text));
        terminal.getWriter().flush();
        if(input) {
            terminal.getWriter().append("\n");
            terminal.getWriter().append(Color.translate("&0CloudSystem &2» &1"));
        }
    }

    public void closeAppend(String prefix, String text) {
        if(input) {
            closeInput();
        }
        terminal.getWriter().append(Color.translate(prefix)).append(Color.translate(text));
        terminal.getWriter().flush();
    }

    public void openAppend(String prefix, String text) {
        if(!input) {
            startInput();
        }
        terminal.getWriter().append(Color.translate(prefix)).append(Color.translate(text));
        terminal.getWriter().flush();
    }

    public void append(String prefix, String text) {
        terminal.getWriter().append(Color.translate(prefix)).append(Color.translate(text));
        terminal.getWriter().flush();
        if(input) {
            terminal.getWriter().append("\n");
            terminal.getWriter().append(Color.translate(prefix));
        }
    }

    public String read() {
        try {
            return terminal.getReader().readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}