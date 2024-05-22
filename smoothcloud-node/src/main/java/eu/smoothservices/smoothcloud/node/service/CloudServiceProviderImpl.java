package eu.smoothservices.smoothcloud.node.service;

import eu.smoothservices.smoothcloud.api.service.ICloudService;
import eu.smoothservices.smoothcloud.api.service.ICloudServiceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class CloudServiceProviderImpl implements ICloudServiceProvider {
    private final HashMap<String, ICloudService> servicePool = new HashMap<>();

    @Override
    public ICloudService getService(String name) {
        return servicePool.get(name);
    }

    @Override
    public CompletableFuture<ICloudService> getServiceAsync(String name) {
        return CompletableFuture.completedFuture(servicePool.get(name));
    }

    @Override
    public List<ICloudService> getServices() {
        return servicePool.values().stream().toList();
    }

    @Override
    public CompletableFuture<List<ICloudService>> getServicesAsync() {
        return CompletableFuture.completedFuture(getServices());
    }

    @Override
    public void registerService(ICloudService cloudService) {
        servicePool.put(cloudService.getName(), cloudService);
    }

    @Override
    public void unregisterService(ICloudService cloudService) {
        servicePool.remove(cloudService.getName());
    }
}
