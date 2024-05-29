package eu.smoothservices.smoothcloud.node.network.components.screen;

import eu.smoothservices.smoothcloud.node.network.components.Wrapper;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;
import lombok.Getter;

@Getter
public class EnabledScreen {
    private final ServiceId serviceId;
    private final Wrapper wrapper;

    public EnabledScreen(ServiceId serviceId, Wrapper wrapper) {
        this.serviceId = serviceId;
        this.wrapper = wrapper;
    }
}
