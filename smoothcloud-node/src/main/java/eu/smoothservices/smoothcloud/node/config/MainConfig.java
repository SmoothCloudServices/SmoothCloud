package eu.smoothservices.smoothcloud.node.config;

import de.eztxm.config.YamlConfig;

public class MainConfig {
    private final YamlConfig config;

    public MainConfig(String path, String name) {
        this.config = new YamlConfig(path, name);
    }

    public void setHost(String value) {
        this.config.set("Host", value);
    }

    public void setPort(int value) {
        this.config.set("Port", value);
    }

    public void setMemory(String value) {
        this.config.set("Memory", value);
    }

    public void setLanguage(Language value) {
        this.config.set("Language", value.name());
    }

    public void setEulaAgreement(boolean agree) {
        this.config.set("EulaAgreement", agree);
    }

    public String getHost() {
        if (this.config.get("Host") == null) return null;
        return this.config.get("Host").asString();
    }

    public int getPort() {
        if (this.config.get("Port") == null) return -1;
        return this.config.get("Port").asInteger();
    }

    public String getMemory() {
        if (this.config.get("Port") == null) return null;
        return this.config.get("Memory").asString();
    }

    public Language getLanguage() {
        if (this.config.get("Language") == null) return null;
        return Language.valueOf(this.config.get("Language").asString());
    }

    public boolean getEulaAgreement() {
        if (this.config.get("EulaAgreement") == null) return false;
        return this.config.get("EulaAgreement").asBoolean();
    }
}
