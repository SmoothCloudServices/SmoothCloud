package dev.eztxm.cloudsystem.node.config.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigParser {

    private final ObjectMapper objectMapper;

    public ConfigParser() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public Config parseConfig(File file) {
        try {
            return objectMapper.readValue(file, Config.class);
        } catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

}
