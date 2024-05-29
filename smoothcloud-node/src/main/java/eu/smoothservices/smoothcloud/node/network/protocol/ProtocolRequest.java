package eu.smoothservices.smoothcloud.node.network.protocol;

public class ProtocolRequest {

    private int id;
    private Object element;

    public ProtocolRequest(int id, Object element) {
        this.id = id;
        this.element = element;
    }

    public int getId() {
        return id;
    }

    public Object getElement() {
        return element;
    }

}
