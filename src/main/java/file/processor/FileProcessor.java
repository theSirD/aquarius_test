package file.processor;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для обработки содержимого файлов.
 */
public interface FileProcessor {
    /**
     * Обрабатывает содержимое файлов в соответствии с заданным действием.
     *
     * @param fileLines Список строк каждого файла.
     * @param action    Действие, которое необходимо выполнить.
     * @return Карта, содержащая результаты обработки, где ключ - номер строки, а значение - карта результатов для каждого файла.
     */
    Map<String, Map<String, String>> processFilesContent(List<List<String>> fileLines, String action);
}