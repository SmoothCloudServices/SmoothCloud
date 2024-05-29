package eu.smoothservices.smoothcloud.node.network.protocol;

import lombok.Getter;

@Getter
public class ProtocolRequest {
    private final int id;
    private final Object element;

    public ProtocolRequest(int id, Object element) {
        this.id = id;
        this.element = element;
    }
}
