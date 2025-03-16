package config.lineHandler;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ModeLineHandlerTest {

    @Test
    void handleLine_ValidModeLine() {
        ModeLineHandler handler = new ModeLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#mode: dir", config);
        assertEquals("dir", config.get("mode"));
    }

    @Test
    void handleLine_InvalidModeLine() {
        ModeLineHandler handler = new ModeLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#path: /some/path", config);
        assertNull(config.get("mode"));
    }

    @Test
    void canHandle_ValidModeLine() {
        ModeLineHandler handler = new ModeLineHandler();
        assertTrue(handler.canHandle("#mode: dir"));
    }

    @Test
    void canHandle_InvalidModeLine() {
        ModeLineHandler handler = new ModeLineHandler();
        assertFalse(handler.canHandle("#path: /some/path"));
    }
}