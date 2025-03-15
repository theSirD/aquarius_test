package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

public class ReplaceActionHandler extends BaseActionHandler {
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            result.put(String.valueOf(fileNumber), replaceChars(line, fileNumber));
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    @Override
    public boolean canHandle(String action) {
        return "replace".equals(action);
    }

    private String replaceChars(String line, int fileNumber) {
        return line.replace("a", String.valueOf(fileNumber + 1))
                .replace("b", String.valueOf(fileNumber + 2))
                .replace("c", String.valueOf(fileNumber + 3));
    }
}