package eu.smoothservices.smoothcloud.node.terminal;

public interface Terminal {

    void writeLine(String message);
    void writeCleanLine(String message);
    String readLine();
}
