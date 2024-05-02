package eu.smoothcloudservices.smoothcloud.node.network.protocol;

import eu.smoothcloudservices.smoothcloud.node.network.protocol.ProtocolStream;

import java.util.Collection;

public interface IProtocol {

    int getId();

    Collection<Class<?>> getAvailableClasses();

    ProtocolStream createElement(Object element) throws Exception;

    ProtocolStream createEmptyElement();
}
