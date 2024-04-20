package eu.smoothcloudservices.smoothcloud.api.group;

public interface ICloudGroup {

    String getName();
    int getMinOnlineCount();
    int getMaxOnlineCount();
    int getMemory();
    ServerType getType();

}
