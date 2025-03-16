package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

public class JsonGenerator {
    public static String generateJson(Map<String, Object> data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }
}