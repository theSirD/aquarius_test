package json.writer;

import json.generator.JsonGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Класс для сохранения JSON-строк в файл.
 */
public class FileJsonWriter extends BaseJsonWriter {

    public FileJsonWriter(JsonGenerator jsonGenerator) {
        super(jsonGenerator);
    }

    /**
     * Сохраняет JSON-строку в файл с заданным именем.
     *
     * @param data     Карта данных для сохранения в JSON.
     * @param fileName Имя файла для сохранения.
     * @return Абсолютный путь к сохраненному файлу.
     * @throws IOException Если произошла ошибка ввода-вывода при записи в файл.
     */
    @Override
    public String saveJson(Map<String, Object> data, String fileName) throws IOException {
        String json = jsonGenerator.generateJson(data);
        Path filePath = Paths.get(fileName);
        Files.write(filePath, json.getBytes());
        return filePath.toAbsolutePath().toString();
    }
}