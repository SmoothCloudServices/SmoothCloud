package dev.eztxm.cloudsystem.api.group;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CloudGroupProvider {

    CloudGroup getGroup(String name);
    CompletableFuture<CloudGroup> getGroupAsync(String name);

    List<CloudGroup> getGroups();
    CompletableFuture<List<CloudGroup>> getGroupsAsync();

    boolean existsGroup(String name);

    void createGroup(CloudGroup cloudGroup);
    void deleteGroup(CloudGroup cloudGroup);

}
