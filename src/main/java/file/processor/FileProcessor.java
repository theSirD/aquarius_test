package file.processor;

import java.util.List;
import java.util.Map;

public interface FileProcessor {
    Map<String, Map<String, String>> processFilesContent(List<List<String>> fileLines, String action);
}
