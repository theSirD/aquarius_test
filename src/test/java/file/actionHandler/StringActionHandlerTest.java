package file.actionHandler;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class StringActionHandlerTest {

    private final StringActionHandler stringHandler = new StringActionHandler();

    @Test
    void handle_validAction() {
        String line = "Test string";
        int fileNumber = 0;
        String action = "string";

        Map<String, String> result = stringHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("Test string", result.get("0"));
    }

    @Test
    void handle_emptyLine() {
        String line = "";
        int fileNumber = 1;
        String action = "string";

        Map<String, String> result = stringHandler.handle(line, fileNumber, action);

        assertNotNull(result);
        assertEquals("", result.get("1"));
    }

    @Test
    void handle_invalidAction() {
        String line = "Test";
        int fileNumber = 2;
        String action = "invalid";

        Map<String, String> result = stringHandler.handle(line, fileNumber, action);

        assertNull(result);
    }
}