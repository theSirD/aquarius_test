package config.lineHandler;

import java.util.Map;

public class ActionLineHandler extends BaseConfigLineHandler {
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            config.put("action", line.substring(8).trim());
            return;
        }

        super.handleLine(line, config);
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#action:");
    }
}