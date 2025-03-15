package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

public class CountActionHandler extends BaseActionHandler {
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            result.put(String.valueOf(fileNumber), String.valueOf(line.split("\\s+").length));
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    @Override
    public boolean canHandle(String action) {
        return "count".equals(action);
    }
}