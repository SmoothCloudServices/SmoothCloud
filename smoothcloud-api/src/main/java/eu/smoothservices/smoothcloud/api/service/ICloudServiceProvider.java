package eu.smoothservices.smoothcloud.api.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICloudServiceProvider {

    ICloudService getService(String name);
    CompletableFuture<ICloudService> getServiceAsync(String name);

    List<ICloudService> getServices();
    CompletableFuture<List<ICloudService>> getServicesAsync();

    void registerService(ICloudService cloudService);
    void unregisterService(ICloudService cloudService);
}
