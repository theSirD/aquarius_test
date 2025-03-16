package file.actionHandler;

import java.util.Map;

/**
 * Интерфейс для обработчиков действий над строками файла.
 */
public interface ActionHandler {
    /**
     * Обрабатывает строку файла в соответствии с заданным действием.
     *
     * @param line       Строка файла для обработки.
     * @param fileNumber Номер файла, к которому относится строка.
     * @param action     Действие, которое необходимо выполнить.
     * @return Карта, содержащая результат обработки, где ключ - номер строки, а значение - результат.
     */
    Map<String, String> handle(String line, int fileNumber, String action);

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param next Следующий обработчик.
     * @return Следующий обработчик.
     */
    ActionHandler setNext(ActionHandler next);
}