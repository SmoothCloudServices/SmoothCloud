package eu.smoothcloudservices.smoothcloud.node.network.protocol.packet;

import eu.smoothcloudservices.smoothcloud.node.network.protocol.ProtocolBuffer;
import eu.smoothcloudservices.smoothcloud.node.network.protocol.ProtocolStream;

import com.google.gson.reflect.TypeToken;
import eu.smoothcloudservices.smoothcloud.node.util.document.Document;

import java.lang.reflect.Type;

public class Packet extends ProtocolStream {

    private static final Type TYPE = new TypeToken<Packet>() {}.getType();

    protected int id;
    protected Document document;

    @Override
    public void write(ProtocolBuffer out) throws Exception {

    }

    @Override
    public void read(ProtocolBuffer in) throws Exception {

    }
}
