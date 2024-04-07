package dev.eztxm.cloudsystem.api.group;

public interface CloudGroup {

    String getName();
    int getMinOnlineCount();
    int getMaxOnlineCount();
    GroupType getType();

}
