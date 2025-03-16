package config.reader;

import config.Configuration;
import config.lineHandler.ActionLineHandler;
import config.lineHandler.BaseConfigLineHandler;
import config.lineHandler.ModeLineHandler;
import config.lineHandler.PathLineHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationReaderImpl implements ConfigurationReader {
    private final BaseConfigLineHandler _configLineHandlerChain;

    public ConfigurationReaderImpl(BaseConfigLineHandler configLineHandler) {
        _configLineHandlerChain = configLineHandler;
    }

    public Configuration readConfiguration(String configFile, String configId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            Map<String, String> currentConfig = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("") && currentConfig != null)
                    break;

                if (line.startsWith("#" + configId)) {
                    currentConfig = new HashMap<>();
                } else if (currentConfig != null) {
                    _configLineHandlerChain.handleLine(line, currentConfig);
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