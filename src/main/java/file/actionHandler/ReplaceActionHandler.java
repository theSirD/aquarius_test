package file.actionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик действия "replace", заменяющий символы в строке файла.
 */
public class ReplaceActionHandler extends BaseActionHandler {
    /**
     * Обрабатывает строку файла, заменяя символы "a", "b", "c" на номера файлов.
     *
     * @param line       Строка файла для обработки.
     * @param fileNumber Номер файла, к которому относится строка.
     * @param action     Действие, которое необходимо выполнить ("replace").
     * @return Карта, содержащая строку с замененными символами, где ключ - номер файла, а значение - строка.
     */
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (canHandle(action)) {
            Map<String, String> result = new HashMap<>();
            result.put(String.valueOf(fileNumber), replaceChars(line, fileNumber));
            return result;
        }

        return super.handle(line, fileNumber, action);
    }

    /**
     * Проверяет, может ли обработчик обработать действие "replace".
     *
     * @param action Действие для проверки.
     * @return true, если действие равно "replace", иначе false.
     */
    @Override
    public boolean canHandle(String action) {
        return "replace".equals(action);
    }

    /**
     * Заменяет символы "a", "b", "c" в строке на номера файлов.
     *
     * @param line       Строка для замены.
     * @param fileNumber Номер файла.
     * @return Строка с замененными символами.
     */
    private String replaceChars(String line, int fileNumber) {
        return line.replace("a", String.valueOf(fileNumber + 1))
                .replace("b", String.valueOf(fileNumber + 2))
                .replace("c", String.valueOf(fileNumber + 3));
    }
}