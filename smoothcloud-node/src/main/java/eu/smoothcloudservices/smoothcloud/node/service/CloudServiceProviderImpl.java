package eu.smoothcloudservices.smoothcloud.node.service;

import eu.smoothcloudservices.smoothcloud.api.service.CloudService;
import eu.smoothcloudservices.smoothcloud.api.service.CloudServiceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class CloudServiceProviderImpl implements CloudServiceProvider {
    private final HashMap<String, CloudService> servicePool = new HashMap<>();

    @Override
    public CloudService getService(String name) {
        return servicePool.get(name);
    }

    @Override
    public CompletableFuture<CloudService> getServiceAsync(String name) {
        return CompletableFuture.completedFuture(servicePool.get(name));
    }

    @Override
    public List<CloudService> getServices() {
        return servicePool.values().stream().toList();
    }

    @Override
    public CompletableFuture<List<CloudService>> getServicesAsync() {
        return CompletableFuture.completedFuture(getServices());
    }

    @Override
    public void registerService(CloudService cloudService) {
        servicePool.put(cloudService.getName(), cloudService);
    }

    @Override
    public void unregisterService(CloudService cloudService) {
        servicePool.remove(cloudService.getName());
    }
}
