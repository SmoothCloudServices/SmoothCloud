package eu.smoothservices.smoothcloud.client.components.screen;

import eu.smoothservices.smoothcloud.client.components.Wrapper;
import eu.smoothservices.smoothcloud.node.util.service.ServiceId;

public class EnabledScreen {

    private ServiceId serviceId;

    private Wrapper wrapper;

    public EnabledScreen(ServiceId serviceId, Wrapper wrapper) {
        this.serviceId = serviceId;
        this.wrapper = wrapper;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public Wrapper getWrapper() {
        return wrapper;
    }
}
