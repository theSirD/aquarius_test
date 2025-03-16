package config.lineHandler;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PathLineHandlerTest {
    // TODO. Add trimming before commas
    @Test
    void handleLine_ValidPathLine() {
        PathLineHandler handler = new PathLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#path: /some/path,/some/path2", config);
        assertEquals("/some/path,/some/path2", config.get("path"));
    }

    @Test
    void handleLine_InvalidPathLine() {
        PathLineHandler handler = new PathLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#mode: dir", config);
        assertNull(config.get("path"));
    }

    @Test
    void canHandle_ValidPathLine() {
        PathLineHandler handler = new PathLineHandler();
        assertTrue(handler.canHandle("#path: /some/path"));
    }

    @Test
    void canHandle_InvalidPathLine() {
        PathLineHandler handler = new PathLineHandler();
        assertFalse(handler.canHandle("#mode: dir"));
    }
}