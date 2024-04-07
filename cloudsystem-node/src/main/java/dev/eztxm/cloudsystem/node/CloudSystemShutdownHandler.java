package dev.eztxm.cloudsystem.node;

public final class CloudSystemShutdownHandler {

    public static void run() {
        ((CloudSystemNode) CloudSystemNode.getInstance()).getTerminal().close();
    }
}
