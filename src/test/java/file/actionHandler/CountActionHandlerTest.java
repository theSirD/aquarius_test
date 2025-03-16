package file.actionHandler;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CountActionHandlerTest {
    private final CountActionHandler countHandler = new CountActionHandler();

    @Test
    void handle_validAction() {
        String line = "This is a test line";
        int fileNumber = 1;
        String action = "count";

        Map<String, String> result = countHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("5", result.get("1"));
    }

    @Test
    void handle_emptyLine() {
        String line = "   ";
        int fileNumber = 2;
        String action = "count";

        Map<String, String> result = countHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("0", result.get("2"));
    }

    @Test
    void handle_invalidAction() {
        String line = "Test";
        int fileNumber = 3;
        String action = "invalid";

        Map<String, String> result = countHandler.handle(line, fileNumber, action);

        assertNull(result);
    }
}