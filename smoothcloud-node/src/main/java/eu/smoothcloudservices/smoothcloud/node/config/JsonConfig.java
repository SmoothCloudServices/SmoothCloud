package eu.smoothcloudservices.smoothcloud.node.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.underscore.U;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;


public class JsonConfig {
    private final String configPath;
    private final String configName;
    private final JSONObject config;

    public JsonConfig(String path, String configName) {
        this.configPath = path;
        this.configName = STR."\{configName}";
        File folder = new File(path);
        File configFile = new File(STR."\{path}/\{this.configName}");
        if (!folder.exists()) folder.mkdir();
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                this.config = new JSONObject();
                saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String configJson = readFile(STR."\{path}/\{this.configName}");
        this.config = new JSONObject(configJson);

    }

    public String getString(String key) {
        try {
            return this.config.getString(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public void setString(String key, String value) {
        this.config.put(key, value);
        saveConfig();
    }

    public int getInteger(String key) {
        try {
            return this.config.getInt(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public void setInteger(String key, int value) {
        this.config.put(key, value);
        saveConfig();
    }

    public long getLong(String key) {
        try {
            return this.config.getLong(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public void setLong(String key, long value) {
        this.config.put(key, value);
        saveConfig();
    }

    public double getDouble(String key) {
        try {
            return this.config.getDouble(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public void setDouble(String key, double value) {
        this.config.put(key, value);
        saveConfig();
    }

    public float getFloat(String key) {
        try {
            return this.config.getFloat(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public void setFloat(String key, float value) {
        this.config.put(key, value);
        saveConfig();
    }

    public boolean getBoolean(String key) {
        try {
            return this.config.getBoolean(key);
        } catch (JSONException e) {
            return false;
        }
    }

    public void setBoolean(String key, boolean value) {
        this.config.put(key, value);
        saveConfig();
    }

    public JSONObject getJsonObject(String key) {
        try {
            return this.config.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public void setJsonObject(String key, JSONObject value) {
        this.config.put(key, value);
        saveConfig();
    }

    public JSONArray getJsonArray(String key) {
        try {
            return this.config.getJSONArray(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public void setJsonArray(String key, JSONArray value) {
        this.config.put(key, value);
        saveConfig();
    }

    public void remove(String key) {
        this.config.remove(key);
        saveConfig();
    }

    public JSONObject toJSONObject() {
        return config;
    }

    private void saveConfig() {
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter file = new FileWriter(STR."\{configPath}/\{configName}")) {
            file.write(U.formatJson(config.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}