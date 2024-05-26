package eu.smoothservices.smoothcloud.node.config;

import dev.eztxm.config.YamlConfig;
import dev.eztxm.object.ObjectConverter;

import java.io.File;

public class MainConfig {
    private final YamlConfig config;

    public MainConfig(File file) {
        this.config = new YamlConfig(file.getPath(), file.getName());
    }

    public void setHost(String value) {
        this.config.set("Host", value);
    }

    public void setPort(int value) {
        this.config.set("Port", value);
    }

    public void setMemory(int value) {
        this.config.set("Memory", value);
    }

    public void setLanguage(Language value) {
        this.config.set("Language", value.name());
    }

    public String getHost() {
        Object host = this.config.get("Host").asObject();
        if (host == null) {
            return null;
        }
        return new ObjectConverter(host).asString();
    }

    public int getPort() {
        return this.config.get("Port").asInteger();
    }

    public int getMemory() {
        return this.config.get("Memory").asInteger();
    }

    public Language getLanguage() {
        return Language.valueOf(this.config.get("Language").asString());
    }
}
