package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик действия "string", возвращающий строку файла без изменений.
 */
public class StringActionHandler extends BaseActionHandler {
    /**
     * Обрабатывает строку файла, возвращая её без изменений.
     *
     * @param line       Строка файла для обработки.
     * @param fileNumber Номер файла, к которому относится строка.
     * @param action     Действие, которое необходимо выполнить ("string").
     * @return Карта, содержащая строку, где ключ - номер файла, а значение - строка.
     */
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            result.put(String.valueOf(fileNumber), line);
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    /**
     * Проверяет, может ли обработчик обработать действие "string".
     *
     * @param action Действие для проверки.
     * @return true, если действие равно "string", иначе false.
     */
    @Override
    public boolean canHandle(String action) {
        return "string".equals(action);
    }
}