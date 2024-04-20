package eu.smoothcloudservices.smoothcloud.node.config;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.toml.TomlFormat;

import java.io.File;

public class CloudConfig {
    private final File file;

    public CloudConfig(File file) {
        this.file = file;
        ConfigFormat<?> toml = TomlFormat.instance();
        ConfigParser<?> parser = toml.createParser();
        Config config = Config.inMemory();
        parser.parse("");
    }
}
