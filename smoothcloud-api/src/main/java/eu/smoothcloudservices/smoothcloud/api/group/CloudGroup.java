package eu.smoothcloudservices.smoothcloud.api.group;

public interface CloudGroup {

    String getName();
    int getMinOnlineCount();
    int getMaxOnlineCount();
    GroupType getType();

}
