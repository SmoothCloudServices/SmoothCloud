package eu.smoothservices.smoothcloud.node.terminal.impl;

import eu.smoothservices.smoothcloud.node.terminal.JavaColor;
import eu.smoothservices.smoothcloud.node.terminal.Terminal;

import java.util.Scanner;

public class SetupTerminal implements Terminal {
    private final String prefix = JavaColor.apply("&9Smooth&bCloud&8-&2Setup &8» &7");

    @Override
    public void writeLine(String message) {
        System.out.println(prefix + message);
    }

    @Override
    public void writeCleanLine(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prefix);
        return scanner.nextLine().trim();
    }
}
