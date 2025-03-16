package config.configurationReader;

import config.Configuration;
import config.configLineHandler.ActionLineHandler;
import config.configLineHandler.ModeLineHandler;
import config.configLineHandler.PathLineHandler;
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

            ModeLineHandler modeHandler = new ModeLineHandler();
            PathLineHandler pathHandler = new PathLineHandler();
            ActionLineHandler actionHandler = new ActionLineHandler();

            modeHandler.setNext(pathHandler).setNext(actionHandler);

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#" + configId)) {
                    currentConfig = new HashMap<>();
                } else if (currentConfig != null) {
                    modeHandler.handleLine(line, currentConfig);
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