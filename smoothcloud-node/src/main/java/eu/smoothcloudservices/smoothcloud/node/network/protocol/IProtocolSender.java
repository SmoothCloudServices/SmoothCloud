package eu.smoothcloudservices.smoothcloud.node.network.protocol;

public interface IProtocolSender {

    void send(Object object);

    void sendSynchronized(Object object);

    void sendAsynchronized(Object object);

    void send(IProtocol iProtocol, Object element);

    void send(int id, Object element);

    void sendAsynchronized(int id, Object element);

    void sendAsynchronized(IProtocol iProtocol, Object element);

    void sendSynchronized(int id, Object element);

    void sendSynchronized(IProtocol iProtocol, Object element);

}
