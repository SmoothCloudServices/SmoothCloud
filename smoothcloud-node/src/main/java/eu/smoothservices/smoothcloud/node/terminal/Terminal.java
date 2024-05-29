package eu.smoothservices.smoothcloud.node.terminal;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class Terminal {
    private final String name, prefix;

    public Terminal(String name, String prefix) {
        this.name = name;
        this.prefix = JavaColor.apply(prefix);
    }

    public void writeLine(String message) {
        System.out.println(prefix + message);
    }

    public void writeCleanLine(String message) {
        System.out.println(JavaColor.apply(message));
    }

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(StringTemplate.STR."\{JavaColor.colored("#ff5555", System.getProperty("user.name"))}\{JavaColor.apply("&7@")}" + prefix);
        return scanner.nextLine().trim();
    }
}
