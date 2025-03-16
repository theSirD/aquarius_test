package file.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация интерфейса FileReader для чтения файлов из директории или списка файлов.
 */
public class FileReaderImpl implements FileReader {
    /**
     * Возвращает список файлов в соответствии с заданным режимом и путем.
     *
     * @param mode Режим работы ("dir" или "files").
     * @param path Путь к директории или список файлов, разделенных запятыми.
     * @return Список объектов File.
     */
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

    /**
     * Возвращает содержимое файлов в виде списка строк для каждого файла.
     *
     * @param files Список файлов для чтения.
     * @return Список списков строк, где каждый внутренний список представляет содержимое файла.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    public List<List<String>> getFilesContent(List<File> files) throws IOException {
        List<List<String>> fileLines = new ArrayList<>();
        for (File file : files) {
            fileLines.add(getFileContent(file));
        }
        return fileLines;
    }

    /**
     * Возвращает содержимое одного файла в виде списка строк.
     *
     * @param file Файл для чтения.
     * @return Список строк, представляющий содержимое файла.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    private List<String> getFileContent(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}