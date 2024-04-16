package eu.smoothcloudservices.smoothcloud.node;

public final class SmoothCloudShutdownHandler {

    public static void run() {
        ((SmoothSmoothCloudNode) SmoothSmoothCloudNode.getInstance()).getTerminal().close();
    }
}
