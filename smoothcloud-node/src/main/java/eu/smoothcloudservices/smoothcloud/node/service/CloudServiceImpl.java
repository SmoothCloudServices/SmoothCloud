package eu.smoothcloudservices.smoothcloud.node.service;

import eu.smoothcloudservices.smoothcloud.api.group.CloudGroup;
import eu.smoothcloudservices.smoothcloud.api.service.CloudService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
public final class CloudServiceImpl implements CloudService {
    private String name;
    private String groupName;
    private CloudGroup group;
    private List<UUID> players;
    private int playerCount;
    private int maxPlayerCount;

    private Process process;

    @Override
    public CompletableFuture<CloudGroup> getGroupAsync() {
        return CompletableFuture.completedFuture(group);
    }

    @Override
    public CompletableFuture<List<UUID>> getPlayersAsync() {
        return CompletableFuture.completedFuture(players);
    }

    @Override
    public void stop() {
         // TODO
    }
}
