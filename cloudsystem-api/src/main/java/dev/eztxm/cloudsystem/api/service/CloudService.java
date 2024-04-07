package dev.eztxm.cloudsystem.api.service;

import dev.eztxm.cloudsystem.api.group.CloudGroup;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CloudService {

    String getName();
    String getGroupName();

    CloudGroup getGroup();
    CompletableFuture<CloudGroup> getGroupAsync();

    List<UUID> getPlayers();
    CompletableFuture<List<UUID>> getPlayersAsync();

    int getPlayerCount();
    int getMaxPlayerCount();

    void stop();

}
