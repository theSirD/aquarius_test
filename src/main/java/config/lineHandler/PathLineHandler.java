package config.lineHandler;

import java.util.Map;

/**
 * Обработчик строки конфигурации, определяющей путь (path).
 */
public class PathLineHandler extends BaseConfigLineHandler {

    /**
     * Обрабатывает строку, если она начинается с "#path:".
     * Извлекает значение пути, нормализует его и сохраняет в карту конфигурации.
     *
     * @param line   Строка для обработки.
     * @param config Карта конфигурации, в которую сохраняются значения.
     */
    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (canHandle(line)) {
            String pathValue = line.substring(6).trim();
            String[] paths = pathValue.split(",");
            StringBuilder normalizedPaths = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                normalizedPaths.append(paths[i].trim());
                if (i < paths.length - 1) {
                    normalizedPaths.append(",");
                }
            }
            config.put("path", normalizedPaths.toString());
            return;
        }

        super.handleLine(line, config);
    }

    /**
     * Проверяет, может ли обработчик обработать данную строку.
     *
     * @param line Строка для проверки.
     * @return true, если строка начинается с "#path:", иначе false.
     */
    @Override
    public boolean canHandle(String line) {
        return line.startsWith("#path:");
    }
}