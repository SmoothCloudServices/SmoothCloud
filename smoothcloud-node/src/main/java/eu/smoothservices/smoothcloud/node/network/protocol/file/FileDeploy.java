package eu.smoothservices.smoothcloud.node.network.protocol.file;

import eu.smoothservices.smoothcloud.node.network.protocol.ProtocolBuffer;
import eu.smoothservices.smoothcloud.node.network.protocol.ProtocolStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDeploy extends ProtocolStream {

    protected String dest;

    protected byte[] bytes;

    public FileDeploy(String dest, byte[] bytes) {
        this.dest = dest;
        this.bytes = bytes;
    }

    public FileDeploy() {
    }

    @Override
    public void write(ProtocolBuffer out) throws Exception {
        out.writeString(dest);
        out.writeVarInt(bytes.length);
        out.writeBytes(bytes);
    }

    @Override
    public void read(ProtocolBuffer in) throws Exception {
        if (in.readableBytes() != 0) {
            this.dest = in.readString();
            this.bytes = in.readBytes(in.readVarInt()).array();
            toWrite();
        }
    }

    public void toWrite() {
        try {
            File file = new File(dest);
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
