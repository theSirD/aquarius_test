package file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessor {
    public Map<String, Map<String, String>> processFilesContent(List<List<String>> fileLines, String action) {
        Map<String, Map<String, String>> out = new HashMap<>();
        int maxLines = fileLines.stream().mapToInt(List::size).max().orElse(0);

        for (int i = 0; i < maxLines; i++) {
            Map<String, String> lineData = new HashMap<>();
            for (int j = 0; j < fileLines.size(); j++) {
                List<String> lines = fileLines.get(j);
                String line = i < lines.size() ? lines.get(i) : "";
                if ("count".equals(action)) {
                    lineData.put(String.valueOf(j + 1), String.valueOf(line.split("\\s+").length));
                } else if ("replace".equals(action)) {
                    lineData.put(String.valueOf(j + 1), replaceChars(line, j + 1));
                } else {
                    lineData.put(String.valueOf(j + 1), line);
                }
            }
            out.put(String.valueOf(i + 1), lineData);
        }
        return out;
    }

    private String replaceChars(String line, int fileNumber) {
        return line.replace("a", String.valueOf(fileNumber + 1))
                .replace("b", String.valueOf(fileNumber + 2))
                .replace("c", String.valueOf(fileNumber + 3));
    }
}