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
        System.out.println(message);
    }

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prefix);
        return scanner.nextLine().trim();
    }
}
