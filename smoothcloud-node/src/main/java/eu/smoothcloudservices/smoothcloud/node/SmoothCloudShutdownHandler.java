package eu.smoothcloudservices.smoothcloud.node;

public final class SmoothCloudShutdownHandler {

    public static void run() {
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().close();
    }
}
