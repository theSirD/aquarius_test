package config.lineHandler;

import java.util.Map;

public interface ConfigLineHandler {
    void handleLine(String line, Map<String, String> config);
    ConfigLineHandler setNext(ConfigLineHandler next);
}