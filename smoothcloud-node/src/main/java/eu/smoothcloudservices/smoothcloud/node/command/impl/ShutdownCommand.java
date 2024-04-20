package eu.smoothcloudservices.smoothcloud.node.command.impl;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothcloudservices.smoothcloud.node.command.Command;

public final class ShutdownCommand implements Command {

    @Override
    public void execute(String[] args) {
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().closeAppend(SmoothCloudNode.PREFIX, "Systems are shut down");
        System.exit(0);
    }
}
