package config.reader;

import config.configuration.Configuration;

import java.io.IOException;

/**
 * Интерфейс для чтения конфигураций из файла.
 */
public interface ConfigurationReader {
    /**
     * Читает конфигурацию из указанного файла по заданному идентификатору.
     *
     * @param configFile Путь к файлу конфигурации.
     * @param configId   Идентификатор конфигурации.
     * @return Объект Configuration, если конфигурация найдена, иначе null.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    Configuration readConfiguration(String configFile, String configId) throws IOException;
}