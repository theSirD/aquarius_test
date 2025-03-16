package file.actionHandler;

import java.util.Map;

/**
 * Абстрактный базовый класс для обработчиков действий над строками файла.
 * Реализует шаблон "Цепочка обязанностей".
 */
public abstract class BaseActionHandler implements ActionHandler {

    /**
     * Следующий обработчик в цепочке.
     */
    protected ActionHandler nextHandler;

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param next Следующий обработчик.
     * @return Следующий обработчик.
     */
    @Override
    public ActionHandler setNext(ActionHandler next) {
        this.nextHandler = next;
        return next;
    }

    /**
     * Обрабатывает строку файла, передавая её следующему обработчику, если он существует.
     *
     * @param line       Строка файла для обработки.
     * @param fileNumber Номер файла, к которому относится строка.
     * @param action     Действие, которое необходимо выполнить.
     * @return Результат обработки от следующего обработчика или null, если следующий обработчик отсутствует.
     */
    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (nextHandler != null) {
            return nextHandler.handle(line, fileNumber, action);
        }
        return null;
    }

    /**
     * Проверяет, может ли обработчик обработать данное действие.
     *
     * @param action Действие для проверки.
     * @return true, если действие соответствует критериям обработчика, иначе false.
     */
    protected abstract boolean canHandle(String action);
}