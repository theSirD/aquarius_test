package file.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для чтения файлов.
 */
public interface FileReader {
    /**
     * Возвращает список файлов в соответствии с заданным режимом и путем.
     *
     * @param mode Режим работы ("dir" или "files").
     * @param path Путь к директории или список файлов, разделенных запятыми.
     * @return Список объектов File.
     */
    List<File> getFiles(String mode, String path);

    /**
     * Возвращает содержимое файлов в виде списка строк для каждого файла.
     *
     * @param files Список файлов для чтения.
     * @return Список списков строк, где каждый внутренний список представляет содержимое файла.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    List<List<String>> getFilesContent(List<File> files) throws IOException;
}