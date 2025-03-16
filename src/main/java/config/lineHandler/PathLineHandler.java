package config.lineHandler;

import java.util.Map;

public class PathLineHandler extends BaseConfigLineHandler {
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            String pathValue = line.substring(6).trim();
            String[] paths = pathValue.split(",");
            StringBuilder normalizedPaths = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                normalizedPaths.append(paths[i].trim());
                if (i < paths.length - 1) {
                    normalizedPaths.append(",");
                }
            }
            config.put("path", normalizedPaths.toString());
            return;
        }

        super.handleLine(line, config);
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#path:");
    }
}