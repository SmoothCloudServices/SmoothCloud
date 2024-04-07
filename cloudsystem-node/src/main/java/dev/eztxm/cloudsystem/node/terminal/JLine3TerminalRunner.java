package dev.eztxm.cloudsystem.node.terminal;

import dev.eztxm.cloudsystem.node.CloudSystemNode;

public final class JLine3TerminalRunner extends Thread {
    private final JLine3Terminal terminal;

    public JLine3TerminalRunner(JLine3Terminal terminal) {
        this.terminal = terminal;

        setDaemon(false);
        setName("JLine3TerminalRunner");
        setPriority(1);
        start();
    }

    @Override
    public void run() {
        String line;
        while ((line = terminal.getLineReader().readLine(Color.translate("&0CloudSystem &2» &1"))) != null) {
            ((CloudSystemNode) CloudSystemNode.getInstance()).getCommandProvider().call(line.split(" "));
        }
    }
}
