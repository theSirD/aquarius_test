package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

public class StringActionHandler extends BaseActionHandler {
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            result.put(String.valueOf(fileNumber), line);
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    @Override
    public boolean canHandle(String action) {
        return "string".equals(action);
    }
}