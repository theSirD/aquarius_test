package file.actionHandler;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ReplaceActionHandlerTest {

    private final ReplaceActionHandler replaceHandler = new ReplaceActionHandler();

    @Test
    void handle_validAction() {
        String line = "abc";
        int fileNumber = 0;
        String action = "replace";

        Map<String, String> result = replaceHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("123", result.get("0"));
    }

    @Test
    void handle_noReplacements() {
        String line = "xyz";
        int fileNumber = 1;
        String action = "replace";

        Map<String, String> result = replaceHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("xyz", result.get("1"));
    }

    @Test
    void handle_invalidAction() {
        String line = "abc";
        int fileNumber = 2;
        String action = "invalid";

        Map<String, String> result = replaceHandler.handle(line, fileNumber, action);

        assertNull(result);
    }
}