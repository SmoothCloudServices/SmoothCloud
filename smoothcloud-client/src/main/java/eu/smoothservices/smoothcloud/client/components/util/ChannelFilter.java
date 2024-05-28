package eu.smoothservices.smoothcloud.client.components.util;

import eu.smoothservices.smoothcloud.client.components.INetworkComponent;

public interface ChannelFilter {

    boolean accept(INetworkComponent networkComponent);

}
