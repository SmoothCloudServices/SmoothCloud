package eu.smoothservices.smoothcloud.api.group;

public interface ICloudGroup {

    String getName();
    String getTemplateName();
    int getMinMemory();
    int getMaxMemory();
    int getMaxPlayers();
    int getMinOnlineCount();
    int getMaxOnlineCount();
    String getPermission();
    boolean isMaintenance();
    boolean isStaticService();
    int getPriority();
    String getWrapperName();
    int getPercentOfPlayersOnGroupToStartNewService();
    ServerType getType();
    String getVersion();
    int getJavaVersion();

}
