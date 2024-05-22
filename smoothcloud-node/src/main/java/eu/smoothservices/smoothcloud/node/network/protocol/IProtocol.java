package eu.smoothservices.smoothcloud.node.network.protocol;

import java.util.Collection;

public interface IProtocol {

    int getId();

    Collection<Class<?>> getAvailableClasses();

    ProtocolStream createElement(Object element) throws Exception;

    ProtocolStream createEmptyElement();
}
