package json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonWriter {

    public static String saveJson(String json, String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        Files.write(filePath, json.getBytes());
        return filePath.toAbsolutePath().toString();
    }
}