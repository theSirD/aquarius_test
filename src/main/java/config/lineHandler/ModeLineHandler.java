package config.lineHandler;
import java.util.Map;

public class ModeLineHandler extends BaseConfigLineHandler {
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            config.put("mode", line.substring(6).trim());
            return;
        }
        super.handleLine(line, config);
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#mode:");
    }
}