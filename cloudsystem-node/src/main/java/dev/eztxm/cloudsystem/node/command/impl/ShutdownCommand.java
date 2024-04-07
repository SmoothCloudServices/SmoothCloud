package dev.eztxm.cloudsystem.node.command.impl;

import dev.eztxm.cloudsystem.node.command.Command;

public final class ShutdownCommand implements Command {

    @Override
    public void execute(String[] args) {
        System.out.println("CloudSystem » Systems are shut down");
        System.exit(0);
    }
}
