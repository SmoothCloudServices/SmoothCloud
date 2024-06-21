package eu.smoothservices.smoothcloud.api.player;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.api.service.ICloudService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ICloudPlayerProvider {

    void connect(ICloudPlayer cloudPlayer, ICloudService cloudService);

    void connect(ICloudPlayer cloudPlayer, String serviceName);

    void connect(ICloudPlayer cloudPlayer, ICloudGroup cloudGroup, PlayerState playerState);

    void connect(ICloudPlayer cloudPlayer, String groupName, PlayerState playerState);

    void sendMessage(String message);

    void sendToFallback();

    void sendToFallback(ICloudGroup cloudGroup);

    void sendToFallback(ICloudGroup cloudGroup, PlayerState playerState);

    void sendToFallback(String groupName);

    void sendToFallback(String groupName, PlayerState playerState);

    ICloudPlayer getPlayer(String playerName);

    ICloudPlayer getPlayer(UUID uniqueId);

    CompletableFuture<ICloudPlayer> getPlayerAsync(String playerName);

    CompletableFuture<ICloudPlayer> getPlayerAsync(UUID uniqueId);

    List<ICloudPlayer> getPlayers();

    CompletableFuture<List<ICloudPlayer>> getPlayersAsync();

    void registerPlayer(ICloudPlayer cloudPlayer);

    void unregisterPlayer(ICloudPlayer cloudPlayer);

    void kickPlayer(ICloudPlayer cloudPlayer);
}
