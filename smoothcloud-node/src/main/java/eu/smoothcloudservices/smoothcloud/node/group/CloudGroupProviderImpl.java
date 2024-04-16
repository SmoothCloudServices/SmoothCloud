package eu.smoothcloudservices.smoothcloud.node.group;

import eu.smoothcloudservices.smoothcloud.api.group.CloudGroup;
import eu.smoothcloudservices.smoothcloud.api.group.CloudGroupProvider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class CloudGroupProviderImpl implements CloudGroupProvider {
    private final HashMap<String, CloudGroup> groupPool = new HashMap<>();

    @Override
    public CloudGroup getGroup(String name) {
        return groupPool.get(name);
    }

    @Override
    public CompletableFuture<CloudGroup> getGroupAsync(String name) {
        return CompletableFuture.completedFuture(groupPool.get(name));
    }

    @Override
    public List<CloudGroup> getGroups() {
        return groupPool.values().stream().toList();
    }

    @Override
    public CompletableFuture<List<CloudGroup>> getGroupsAsync() {
        return CompletableFuture.completedFuture(getGroups());
    }

    @Override
    public boolean existsGroup(String name) {
        return groupPool.containsKey(name);
    }

    @Override
    public void createGroup(CloudGroup cloudGroup) {
        groupPool.put(cloudGroup.getName(), cloudGroup);
    }

    @Override
    public void deleteGroup(CloudGroup cloudGroup) {
        groupPool.remove(cloudGroup.getName());
    }
}
