package eu.smoothservices.smoothcloud.node.network.protocol;


public abstract class ProtocolStream {

    public ProtocolStream() {
    }

    public abstract void write(ProtocolBuffer out) throws Exception;

    public abstract void read(ProtocolBuffer in) throws Exception;

}
