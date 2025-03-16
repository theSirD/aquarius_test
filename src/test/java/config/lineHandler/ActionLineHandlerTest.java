package config.lineHandler;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ActionLineHandlerTest {
    @Test
    void handleLine_ValidActionLine() {
        ActionLineHandler handler = new ActionLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#action: string", config);
        assertEquals("string", config.get("action"));
    }

    @Test
    void handleLine_InvalidActionLine() {
        ActionLineHandler handler = new ActionLineHandler();
        Map<String, String> config = new HashMap<>();
        handler.handleLine("#mode: dir", config);
        assertNull(config.get("action"));
    }

    @Test
    void canHandle_ValidActionLine() {
        ActionLineHandler handler = new ActionLineHandler();
        assertTrue(handler.canHandle("#action: string"));
    }

    @Test
    void canHandle_InvalidActionLine() {
        ActionLineHandler handler = new ActionLineHandler();
        assertFalse(handler.canHandle("#mode: dir"));
    }
}