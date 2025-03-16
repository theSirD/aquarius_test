package config.lineHandler;

import java.util.Map;

/**
 * Абстрактный базовый класс для обработчиков строк конфигурации.
 * Реализует шаблон "Цепочка обязанностей".
 */
public abstract class BaseConfigLineHandler implements ConfigLineHandler {
    /**
     * Следующий обработчик в цепочке.
     */
    protected ConfigLineHandler nextHandler;

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param next Следующий обработчик.
     * @return Следующий обработчик.
     */
    @Override
    public ConfigLineHandler setNext(ConfigLineHandler next) {
        this.nextHandler = next;
        return next;
    }

    /**
     * Обрабатывает строку, передавая её следующему обработчику, если он существует.
     *
     * @param line   Строка для обработки.
     * @param config Карта конфигурации.
     */
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (nextHandler != null) {
            nextHandler.handleLine(line, config);
        }
    }

    /**
     * Проверяет, может ли обработчик обработать данную строку.
     *
     * @param line Строка для проверки.
     * @return true, если строка соответствует критериям обработчика, иначе false.
     */
    protected abstract boolean canHandle(String line);
}