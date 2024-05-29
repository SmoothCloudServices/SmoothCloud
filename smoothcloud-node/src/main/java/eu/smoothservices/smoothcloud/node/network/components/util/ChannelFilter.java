package eu.smoothservices.smoothcloud.node.network.components.util;

import eu.smoothservices.smoothcloud.node.network.components.INetworkComponent;

public interface ChannelFilter {

    boolean accept(INetworkComponent networkComponent);

}
