package eu.smoothcloudservices.smoothcloud.api.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CloudServiceProvider {

    CloudService getService(String name);
    CompletableFuture<CloudService> getServiceAsync(String name);

    List<CloudService> getServices();
    CompletableFuture<List<CloudService>> getServicesAsync();

    void registerService(CloudService cloudService);
    void unregisterService(CloudService cloudService);

}
