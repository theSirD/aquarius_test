package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

/**
 * Класс для генерации JSON-строк из объектов.
 */
public class JsonGenerator {
    /**
     * Генерирует JSON-строку из заданной карты данных.
     *
     * @param data Карта данных для преобразования в JSON.
     * @return JSON-строка, представляющая данные.
     */
    public static String generateJson(Map<String, Object> data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }
}