package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик действия "count", подсчитывающий количество слов в строке файла.
 */
public class CountActionHandler extends BaseActionHandler {
    /**
     * Обрабатывает строку файла, подсчитывая количество слов.
     *
     * @param line       Строка файла для обработки.
     * @param fileNumber Номер файла, к которому относится строка.
     * @param action     Действие, которое необходимо выполнить ("count").
     * @return Карта, содержащая количество слов, где ключ - номер файла, а значение - количество слов.
     */
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

    /**
     * Проверяет, может ли обработчик обработать действие "count".
     *
     * @param action Действие для проверки.
     * @return true, если действие равно "count", иначе false.
     */
    @Override
    public boolean canHandle(String action) {
        return "count".equals(action);
    }
}