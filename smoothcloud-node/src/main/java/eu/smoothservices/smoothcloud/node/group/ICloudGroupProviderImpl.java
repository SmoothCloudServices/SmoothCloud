package eu.smoothservices.smoothcloud.node.group;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.api.group.ICloudGroupProvider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ICloudGroupProviderImpl implements ICloudGroupProvider {
    private final HashMap<String, ICloudGroup> groupPool = new HashMap<>();

    @Override
    public ICloudGroup getGroup(String name) {
        return groupPool.get(name);
    }

    @Override
    public CompletableFuture<ICloudGroup> getGroupAsync(String name) {
        return CompletableFuture.completedFuture(groupPool.get(name));
    }

    @Override
    public List<ICloudGroup> getGroups() {
        return groupPool.values().stream().toList();
    }

    @Override
    public CompletableFuture<List<ICloudGroup>> getGroupsAsync() {
        return CompletableFuture.completedFuture(getGroups());
    }

    @Override
    public boolean existsGroup(String name) {
        return groupPool.containsKey(name);
    }

    @Override
    public void createGroup(ICloudGroup ICloudGroup) {
        groupPool.put(ICloudGroup.getName(), ICloudGroup);
    }

    @Override
    public void deleteGroup(ICloudGroup ICloudGroup) {
        groupPool.remove(ICloudGroup.getName());
    }
}
