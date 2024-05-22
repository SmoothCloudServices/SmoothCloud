package eu.smoothservices.smoothcloud.node;

public final class SmoothCloudShutdownHandler {

    public static void run() {
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().shutdown();
    }
}
