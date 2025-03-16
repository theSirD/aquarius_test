package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

public class CountActionHandler extends BaseActionHandler {
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            int wordCount;
            if (!line.trim().isEmpty()) {
                wordCount = line.split("\\s+").length;
            } else {
                wordCount = 0;
            }
            result.put(String.valueOf(fileNumber), String.valueOf(wordCount));
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    @Override
    public boolean canHandle(String action) {
        return "count".equals(action);
    }
}