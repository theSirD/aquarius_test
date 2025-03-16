package config.lineHandler;

import java.util.Map;

/**
 * Обработчик строки конфигурации, определяющей действие (action).
 */
public class ActionLineHandler extends BaseConfigLineHandler {

    /**
     * Обрабатывает строку, если она начинается с "#action:".
     * Извлекает значение действия и сохраняет его в карту конфигурации.
     *
     * @param line   Строка для обработки.
     * @param config Карта конфигурации, в которую сохраняются значения.
     */
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            config.put("action", line.substring(8).trim());
            return;
        }

        super.handleLine(line, config);
    }

    /**
     * Проверяет, может ли обработчик обработать данную строку.
     *
     * @param line Строка для проверки.
     * @return true, если строка начинается с "#action:", иначе false.
     */
    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#action:");
    }
}