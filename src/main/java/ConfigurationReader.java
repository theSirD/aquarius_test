import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {

    public static Configuration readConfiguration(String configFile, String configId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            Map<String, String> currentConfig = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#" + configId)) {
                    currentConfig = new HashMap<>();
                } else if (currentConfig != null) {
                    if (line.startsWith("#mode:")) {
                        currentConfig.put("mode", line.substring(6).trim());
                    } else if (line.startsWith("#path:")) {
                        currentConfig.put("path", line.substring(6).trim());
                    } else if (line.startsWith("#action:")) {
                        currentConfig.put("action", line.substring(8).trim());
                    }
                }
            }

            if (currentConfig != null) {
                return new Configuration(currentConfig.get("mode"), currentConfig.get("path"), currentConfig.get("action"));
            } else {
                return null;
            }
        }
    }
}