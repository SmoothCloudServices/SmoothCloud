package eu.smoothservices.smoothcloud.api.player;

import eu.smoothservices.smoothcloud.api.service.ICloudService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICloudPlayerProvider {

    void connect(ICloudService ICloudService);
    void connect(String serviceName);
    void sendMessage(String message);
    void sendToFallback();

    ICloudPlayer getPlayer(String name);
    CompletableFuture<ICloudPlayer> getPlayerAsync(String name);

    List<ICloudPlayer> getPlayers();
    CompletableFuture<List<ICloudPlayer>> getPlayersAsync();

    void registerPlayer(ICloudPlayer cloudPlayer);
    void unregisterPlayer(ICloudPlayer cloudPlayer);
}
