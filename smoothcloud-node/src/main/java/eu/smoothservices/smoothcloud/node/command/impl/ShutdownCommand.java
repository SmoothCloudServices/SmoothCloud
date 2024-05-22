package eu.smoothservices.smoothcloud.node.command.impl;

import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.node.command.Command;

public final class ShutdownCommand implements Command {

    @Override
    public void execute(String[] args) {
        ((SmoothCloudNode) SmoothCloudNode.getInstance()).getTerminal().closeAppend(SmoothCloudNode.PREFIX, "Systems are shut down");
        System.exit(0);
    }
}
