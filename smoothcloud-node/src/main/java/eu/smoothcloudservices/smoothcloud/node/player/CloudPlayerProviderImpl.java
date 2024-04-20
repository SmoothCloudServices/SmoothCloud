package eu.smoothcloudservices.smoothcloud.node.player;

import eu.smoothcloudservices.smoothcloud.api.player.ICloudPlayer;
import eu.smoothcloudservices.smoothcloud.api.player.ICloudPlayerProvider;
import eu.smoothcloudservices.smoothcloud.api.service.ICloudService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CloudPlayerProviderImpl implements ICloudPlayerProvider {

    @Override
    public void connect(ICloudService ICloudService) {

    }

    @Override
    public void connect(String serviceName) {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendToFallback() {

    }

    @Override
    public ICloudPlayer getPlayer(String name) {
        return null;
    }

    @Override
    public CompletableFuture<ICloudPlayer> getPlayerAsync(String name) {
        return null;
    }

    @Override
    public List<ICloudPlayer> getPlayers() {
        return List.of();
    }

    @Override
    public CompletableFuture<List<ICloudPlayer>> getPlayersAsync() {
        return null;
    }

    @Override
    public void registerPlayer(ICloudPlayer cloudPlayer) {

    }

    @Override
    public void unregisterPlayer(ICloudPlayer cloudPlayer) {

    }
}
