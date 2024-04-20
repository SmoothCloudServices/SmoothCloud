package eu.smoothcloudservices.smoothcloud.api.group;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICloudGroupProvider {

    ICloudGroup getGroup(String name);
    CompletableFuture<ICloudGroup> getGroupAsync(String name);

    List<ICloudGroup> getGroups();
    CompletableFuture<List<ICloudGroup>> getGroupsAsync();

    boolean existsGroup(String name);

    void createGroup(ICloudGroup cloudGroup);
    void deleteGroup(ICloudGroup cloudGroup);
}
