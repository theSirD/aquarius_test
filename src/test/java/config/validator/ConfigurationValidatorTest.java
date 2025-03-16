package config.validator;

import config.configuration.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationValidatorTest {

    private ConfigurationValidator validator;
    private Set<String> allowedActions;

    @BeforeEach
    void setUp() {
        allowedActions = Set.of("string", "count", "replace");
        validator = new ConfigurationValidator(allowedActions);
    }

    @Test
    void validate_validConfig() {
        Configuration config = new Configuration("dir", "/path/to/dir", "string");
        assertDoesNotThrow(() -> validator.validate(config));
    }

    @Test
    void validate_nullConfig() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(null));
    }

    @Test
    void validate_invalidMode() {
        Configuration config = new Configuration("invalid", "/path/to/dir", "string");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));
    }

    @Test
    void validate_nullPath() {
        Configuration config = new Configuration("dir", null, "string");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));
    }

    @Test
    void validate_emptyPath() {
        Configuration config = new Configuration("dir", "   ", "string");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));
    }

    @Test
    void validate_multiplePathsForDirMode() {
        Configuration config = new Configuration("dir", "/path/to/dir1,/path/to/dir2", "string");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));
    }

    @Test
    void validate_invalidAction() {
        Configuration config = new Configuration("dir", "/path/to/dir", "invalid");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));
    }

    @Test
    void validate_newAction() {
        Configuration config = new Configuration("dir", "/path/to/dir", "newAction");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(config));

        allowedActions = Set.of("string", "count", "replace", "newAction");
        validator = new ConfigurationValidator(allowedActions);
        assertDoesNotThrow(() -> validator.validate(config));
    }
}