package eu.smoothservices.smoothcloud.node.terminal;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CloudTerminal {
    private final String name;
    private final String prefix;
    private final List<String> cache;
    private final Terminal terminal;
    private final LineReader reader;

    @SneakyThrows
    public CloudTerminal(String name, String prefix, StringsCompleter completer) {
        this.name = name;
        this.prefix = JavaColor.apply(prefix);
        this.cache = new ArrayList<>();
        terminal = TerminalBuilder.builder()
                .dumb(true)
                .color(true)
                .name("SmoothCloud - 0.0.0-INDEV")
                .build();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .parser(new DefaultParser())
                .build();
        clearScreen();
    }

    public void writeLine(String message) {
        cache.add(prefix + message);
        terminal.writer().println(prefix + message);
        terminal.flush();
    }

    public void writeCleanLine(String message) {
        cache.add(JavaColor.apply(message));
        terminal.writer().println(JavaColor.apply(message));
        terminal.flush();
    }

    public void userInput() {
        String userPrompt = JavaColor.colored("#ff5555", System.getProperty("user.name")) + JavaColor.apply("&7@") + prefix;
        terminal.writer().print(userPrompt);
    }

    public String readLine() {
        return reader.readLine().trim();
    }

    public void clearScreen() {
        cache.clear();
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
    }
}
