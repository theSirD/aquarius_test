package config.lineHandler;

import java.util.Map;

/**
 * Интерфейс для обработчиков строк конфигурации.
 */
public interface ConfigLineHandler {
    /**
     * Обрабатывает строку конфигурации.
     *
     * @param line   Строка для обработки.
     * @param config Карта конфигурации, в которую сохраняются значения.
     */
    void handleLine(String line, Map<String, String> config);

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param next Следующий обработчик.
     * @return Следующий обработчик.
     */
    ConfigLineHandler setNext(ConfigLineHandler next);
}