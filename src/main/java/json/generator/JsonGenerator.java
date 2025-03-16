package json.generator;

import java.util.Map;

/**
 * Интерфейс для генерации JSON-строк из объектов.
 */
public interface JsonGenerator {
    /**
     * Генерирует JSON-строку из заданной карты данных.
     *
     * @param data Карта данных для преобразования в JSON.
     * @return JSON-строка, представляющая данные.
     */
    String generateJson(Map<String, Object> data);
}