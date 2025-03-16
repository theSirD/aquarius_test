package config.reader;

import config.configuration.Configuration;
import config.lineHandler.ConfigLineHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса ConfigurationReader для чтения конфигураций из файла.
 * Использует цепочку обработчиков строк для парсинга конфигурации.
 */
public class ConfigurationReaderImpl implements ConfigurationReader {

    /**
     * Цепочка обработчиков строк конфигурации.
     */
    private final ConfigLineHandler _configLineHandlerChain;

    /**
     * Конструктор для создания экземпляра ConfigurationReaderImpl.
     *
     * @param configLineHandler Цепочка обработчиков строк конфигурации.
     */
    public ConfigurationReaderImpl(ConfigLineHandler configLineHandler) {
        _configLineHandlerChain = configLineHandler;
    }

    /**
     * Читает конфигурацию из указанного файла по заданному идентификатору.
     *
     * @param configFile Путь к файлу конфигурации.
     * @param configId   Идентификатор конфигурации.
     * @return Объект Configuration, если конфигурация найдена, иначе null.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    public Configuration readConfiguration(String configFile, String configId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            Map<String, String> currentConfig = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() && currentConfig != null)
                    break;

                if (line.startsWith("#" + configId)) {
                    currentConfig = new HashMap<>();
                } else if (currentConfig != null) {
                    _configLineHandlerChain.handleLine(line, currentConfig);
                }
            }

            if (currentConfig != null) {
                return new Configuration(currentConfig.get("mode"), currentConfig.get("path"), currentConfig.get("action"));
            } else {
                return null;
            }
        }
    }
}