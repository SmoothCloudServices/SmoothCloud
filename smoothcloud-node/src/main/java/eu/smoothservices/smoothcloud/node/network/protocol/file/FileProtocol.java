package eu.smoothservices.smoothcloud.node.network.protocol.file;

import eu.smoothservices.smoothcloud.node.network.protocol.ProtocolStream;
import eu.smoothservices.smoothcloud.node.network.protocol.IProtocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

public class FileProtocol implements IProtocol {

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public Collection<Class<?>> getAvailableClasses() {
        return Arrays.asList(File.class, Path.class, FileDeploy.class);
    }

    @Override
    public ProtocolStream createElement(Object element) {
        if (element.getClass().equals(File.class)) {
            try {
                byte[] input = Files.readAllBytes(Paths.get(((File) element).getPath()));
                String dest = ((File) element).getPath();
                return new FileDeploy(dest, input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (element.getClass().equals(Path.class)) {
            try {
                byte[] input = Files.readAllBytes((Path) element);
                String dest = ((Path) element).toUri().toString();
                return new FileDeploy(dest, input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (element instanceof FileDeploy) {
            return (FileDeploy) element;
        }

        return null;
    }

    @Override
    public ProtocolStream createEmptyElement() {
        return new FileDeploy();
    }
}
