package json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс для сохранения JSON-строк в файл.
 */
public class JsonWriter {
    /**
     * Сохраняет JSON-строку в файл с заданным именем.
     *
     * @param json     JSON-строка для сохранения.
     * @param fileName Имя файла для сохранения.
     * @return Абсолютный путь к сохраненному файлу.
     * @throws IOException Если произошла ошибка ввода-вывода при записи в файл.
     */
    public static String saveJson(String json, String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        Files.write(filePath, json.getBytes());
        return filePath.toAbsolutePath().toString();
    }
}