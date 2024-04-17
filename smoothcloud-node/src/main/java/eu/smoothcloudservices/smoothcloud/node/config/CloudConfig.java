package eu.smoothcloudservices.smoothcloud.node.config;

import eu.smoothcloudservices.smoothcloud.node.config.entity.*;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CloudConfig {
    private final File file;
    private Properties properties;

    @SneakyThrows
    public CloudConfig() {
        this.file = new File("config.cfg");
        this.properties = new Properties();
        if (!file.exists()) file.createNewFile();
    }

    public void set(String key, String value) {
        String fileName = file.getName();
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            properties.load(inputStream);
            properties.setProperty(key, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void save() {
        properties.store(new FileOutputStream("config.cfg"), null);
    }
}
