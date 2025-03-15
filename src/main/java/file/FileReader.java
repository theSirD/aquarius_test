package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileReader {
    public List<File> getFiles(String mode, String path) {
        List<File> files = new ArrayList<>();
        if ("dir".equals(mode)) {
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] fileArray = directory.listFiles();
                if (fileArray != null) {
                    for (File file : fileArray) {
                        if (file.isFile()) {
                            files.add(file);
                        }
                    }
                    files.sort(Comparator.comparing(File::getName));
                }
            }
        } else if ("files".equals(mode)) {
            String[] paths = path.split(",");
            for (String filePath : paths) {
                File file = new File(filePath.trim());
                if (file.exists() && file.isFile()) {
                    files.add(file);
                }
            }
            files.sort(Comparator.comparing(File::getName));
        }
        return files;
    }

    public List<List<String>> getFilesContent(List<File> files) throws IOException {
        List<List<String>> fileLines = new ArrayList<>();
        for (File file : files) {
            fileLines.add(getFileContent(file));
        }
        return fileLines;
    }

    private List<String> getFileContent(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}