package file.processor;

import file.actionHandler.ActionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessorImpl implements FileProcessor {
    private final ActionHandler _actionHandlerChain;

    public FileProcessorImpl(ActionHandler actionHandlerChain) {
        _actionHandlerChain = actionHandlerChain;
    }

    public Map<String, Map<String, String>> processFilesContent(List<List<String>> fileLines, String action) {
        Map<String, Map<String, String>> out = new HashMap<>();
        int maxLines = fileLines.stream().mapToInt(List::size).max().orElse(0);

        for (int i = 0; i < maxLines; i++) {
            Map<String, String> lineData = new HashMap<>();
            for (int j = 0; j < fileLines.size(); j++) {
                List<String> lines = fileLines.get(j);
                String line = i < lines.size() ? lines.get(i) : "";
                lineData.putAll(_actionHandlerChain.handle(line, j + 1, action));
            }
            out.put(String.valueOf(i + 1), lineData);
        }
        return out;
    }
}