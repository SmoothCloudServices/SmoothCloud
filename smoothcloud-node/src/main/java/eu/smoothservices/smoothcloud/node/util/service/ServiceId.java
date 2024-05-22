package eu.smoothservices.smoothcloud.node.util.service;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ServiceId {

    private final String group;
    private final int id;
    private final UUID uniqueId;
    private final String wrapperId;
    private final String serverId;
    private final String gameId;

    public ServiceId(String group, int id, UUID uniqueId, String wrapperId) {
        this.group = group;
        this.id = id;
        this.uniqueId = uniqueId;
        this.wrapperId = wrapperId;

        this.serverId = StringTemplate.STR."\{group}-\{id}";
        this.gameId = uniqueId.toString().split("-")[0];
    }

    public ServiceId(String group, int id, UUID uniqueId, String wrapperId, String serverId) {
        this.group = group;
        this.id = id;
        this.uniqueId = uniqueId;
        this.wrapperId = wrapperId;

        this.serverId = serverId;
        this.gameId = uniqueId.toString().split("-")[0];
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (uniqueId != null ? uniqueId.hashCode() : 0);
        result = 31 * result + (wrapperId != null ? wrapperId.hashCode() : 0);
        result = 31 * result + (serverId != null ? serverId.hashCode() : 0);
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if(this == object) return true;
        if(!(object instanceof ServiceId serviceId)) return false;
        return id == serviceId.id && Objects.equals(group, serviceId.group) && Objects.equals(uniqueId, serviceId.uniqueId)
                && Objects.equals(wrapperId, serviceId.wrapperId) && Objects.equals(serverId, serviceId.serverId) && Objects.equals(gameId, serviceId.gameId);
    }

    @Override
    public String toString() {
        return StringTemplate.STR."\{this.serverId}#\{this.uniqueId.toString()}";
    }

}
