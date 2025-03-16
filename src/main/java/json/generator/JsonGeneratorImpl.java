package json.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

/**
 * Класс для генерации JSON-строк из объектов.
 */
public class JsonGeneratorImpl implements JsonGenerator {

    private final Gson gson;

    public JsonGeneratorImpl() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Генерирует JSON-строку из заданной карты данных.
     *
     * @param data Карта данных для преобразования в JSON.
     * @return JSON-строка, представляющая данные.
     */
    @Override
    public String generateJson(Map<String, Object> data) {
        return gson.toJson(data);
    }
}