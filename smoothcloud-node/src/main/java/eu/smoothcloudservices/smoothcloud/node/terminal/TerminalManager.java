package eu.smoothcloudservices.smoothcloud.node.terminal;

import lombok.Getter;

import java.io.IOException;

@Getter
public class TerminalManager {

    private Terminal terminal;

    public TerminalManager() {
        this.terminal = new Terminal();
    }

    public void shutdown() {
        terminal.shutdown();
    }

    public void append(String text) {
        terminal.getWriter().append(Color.translate("&0CloudSystem &2» &1")).append(Color.translate(text));
        terminal.getWriter().flush();
        terminal.getWriter().append("\n").append(Color.translate("&0CloudSystem &2» &1"));
        terminal.getWriter().flush();
    }

    public String read() {
        try {
            return terminal.getReader().readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
