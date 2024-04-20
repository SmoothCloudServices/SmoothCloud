package eu.smoothcloudservices.smoothcloud.node.service;

import eu.smoothcloudservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothcloudservices.smoothcloud.api.service.ICloudService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
public final class CloudServiceImpl implements ICloudService {
    private String name;
    private String groupName;
    private ICloudGroup group;
    private List<UUID> players;
    private int playerCount;
    private int maxPlayerCount;

    private Process process;

    @Override
    public CompletableFuture<ICloudGroup> getGroupAsync() {
        return CompletableFuture.completedFuture(group);
    }

    @Override
    public CompletableFuture<List<UUID>> getPlayersAsync() {
        return CompletableFuture.completedFuture(players);
    }

    @Override
    public int getMemoryUsage() {
        return 0;
    }

    @Override
    public int getMaxMemory() {
        return 0;
    }

    @Override
    public void stop() {
         // TODO
    }
}
