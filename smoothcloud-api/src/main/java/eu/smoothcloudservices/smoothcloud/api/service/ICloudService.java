package eu.smoothcloudservices.smoothcloud.api.service;

import eu.smoothcloudservices.smoothcloud.api.group.ICloudGroup;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ICloudService {

    String getName();
    String getGroupName();

    ICloudGroup getGroup();
    CompletableFuture<ICloudGroup> getGroupAsync();

    List<UUID> getPlayers();
    CompletableFuture<List<UUID>> getPlayersAsync();

    int getPlayerCount();
    int getMaxPlayerCount();
    int getMemoryUsage();
    int getMaxMemory();

    void stop();

}
