package eu.smoothservices.smoothcloud.client.lib;

import lombok.Getter;

@Getter
public class WrapperInfo {
    private final String serverId;
    private final String hostName;
    private final String version;
    private final boolean ready;
    private final int availableProcessors;
    private final int startPort;
    private final int processQueueSize;
    private final int memory;

    public WrapperInfo(String serverId, String hostName, String version, boolean ready, int availableProcessors, int startPort, int processQueueSize, int memory) {
        this.serverId = serverId;
        this.hostName = hostName;
        this.version = version;
        this.ready = ready;
        this.availableProcessors = availableProcessors;
        this.startPort = startPort;
        this.processQueueSize = processQueueSize;
        this.memory = memory;
    }

    @Override
    public String toString() {
        return StringTemplate.STR."WrapperInfo{serverId='\{serverId}\{'\''}, hostName='\{hostName}\{'\''}, version='\{version}\{'\''}, ready=\{ready}, availableProcessors=\{availableProcessors}, startPort=\{startPort}, process_queue_size=\{processQueueSize}, memory=\{memory}\{'}'}";
    }
}
