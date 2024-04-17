package eu.smoothcloudservices.smoothcloud.node.terminal;

import eu.smoothcloudservices.smoothcloud.node.SmoothCloudNode;

import java.io.File;

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
            if (new File("config.cfg").length() == 0) {
                ((SmoothCloudNode) SmoothCloudNode.getInstance()).getSetup().setup(line.split(" "));
                return;
            }
            ((SmoothCloudNode) SmoothCloudNode.getInstance()).getCommandProvider().call(line.split(" "));
        }
    }
}
