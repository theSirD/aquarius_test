package json.writer;

import json.generator.JsonGenerator;

import java.io.IOException;
import java.util.Map;

/**
 * Абстрактный класс для сохранения JSON-строк в файл.
 */
public abstract class BaseJsonWriter {

    protected final JsonGenerator jsonGenerator;

    public BaseJsonWriter(JsonGenerator jsonGenerator) {
        this.jsonGenerator = jsonGenerator;
    }

    /**
     * Сохраняет JSON-строку в файл с заданным именем.
     *
     * @param data     Карта данных для сохранения в JSON.
     * @param fileName Имя файла для сохранения.
     * @return Абсолютный путь к сохраненному файлу.
     * @throws IOException Если произошла ошибка ввода-вывода при записи в файл.
     */
    public abstract String saveJson(Map<String, Object> data, String fileName) throws IOException;
}