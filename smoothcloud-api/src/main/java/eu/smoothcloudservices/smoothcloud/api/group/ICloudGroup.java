package eu.smoothcloudservices.smoothcloud.api.group;

public interface ICloudGroup {

    String getName();
    String getTemplateName();
    String getVersion();
    String getWrapperName();

    int getMinOnlineCount();
    int getMaxOnlineCount();
    int getMinMemory();
    int getMaxMemory();

    ServerType getType();

    boolean isStaticService();

}
