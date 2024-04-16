package eu.smoothcloudservices.smoothcloud.node.terminal;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class JLine3Terminal {
    private Terminal terminal;

    @Getter
    private LineReader lineReader;
    private JLine3TerminalRunner terminalRunner;

    public JLine3Terminal() {
        try {
            this.terminal = TerminalBuilder.builder()
                    .system(true)
                    .dumb(true)
                    .streams(System.in, System.out)
                    .encoding(StandardCharsets.UTF_8)
                    .build();
            this.lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
                    .build();
            terminalRunner = new JLine3TerminalRunner(this);
        } catch (UserInterruptException | IOException ignored) {}
    }

    @SneakyThrows
    public void close() {
        terminalRunner.interrupt();
        terminal.close();
    }

    public void write(String input) {
        terminal.puts(InfoCmp.Capability.carriage_return);
        terminal.writer().println(Color.translate(input));
        terminal.flush();
    }
}
