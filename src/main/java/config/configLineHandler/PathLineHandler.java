package config.configLineHandler;
import java.util.Map;

public class PathLineHandler extends BaseConfigLineHandler {
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            config.put("path", line.substring(6).trim());
            return;
        }

        super.handleLine(line, config);
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#path:");
    }
}