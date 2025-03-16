package config.reader;

import config.Configuration;
import config.lineHandler.BaseConfigLineHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigurationReaderImplTest {

    @Mock
    private BaseConfigLineHandler configLineHandlerChain;

    private ConfigurationReaderImpl configurationReader;

    @BeforeEach
    void setUp() {
        configurationReader = new ConfigurationReaderImpl(configLineHandlerChain);
    }

    @Test
    void readConfiguration_ValidConfig() throws IOException {
        String configContent = "#config1\n#mode: dir\n#path: /some/path\n#action: string";
        Path tempFile = Files.createTempFile("config", ".txt");
        Files.write(tempFile, configContent.getBytes());

        doAnswer(invocation -> {
            String line = invocation.getArgument(0);
            Map<String, String> config = invocation.getArgument(1);
            if (line.startsWith("#mode:")) {
                config.put("mode", line.substring(6).trim());
            } else if (line.startsWith("#path:")) {
                config.put("path", line.substring(6).trim());
            } else if (line.startsWith("#action:")) {
                config.put("action", line.substring(8).trim());
            }
            return null;
        }).when(configLineHandlerChain).handleLine(anyString(), anyMap());

        Configuration config = configurationReader.readConfiguration(tempFile.toString(), "config1");

        assertNotNull(config);
        assertEquals("dir", config.getMode());
        assertEquals("/some/path", config.getPath());
        assertEquals("string", config.getAction());

        verify(configLineHandlerChain, times(3)).handleLine(anyString(), anyMap());

        Files.delete(tempFile);
    }

    @Test
    void readConfiguration_ConfigNotFound() throws IOException {
        String configContent = "#config2\n#mode: dir\n#path: /some/path\n#action: string";
        Path tempFile = Files.createTempFile("config", ".txt");
        Files.write(tempFile, configContent.getBytes());

        Configuration config = configurationReader.readConfiguration(tempFile.toString(), "config1");

        assertNull(config);

        verify(configLineHandlerChain, never()).handleLine(anyString(), anyMap());

        Files.delete(tempFile);
    }

    @Test
    void readConfiguration_EmptyLines() throws IOException {
        String configContent = "#config1\n#mode: dir\n\n#path: /some/path\n#action: string";
        Path tempFile = Files.createTempFile("config", ".txt");
        Files.write(tempFile, configContent.getBytes());

        doAnswer(invocation -> {
            String line = invocation.getArgument(0);
            Map<String, String> config = invocation.getArgument(1);
            if (line.startsWith("#mode:")) {
                config.put("mode", line.substring(6).trim());
            } else if (line.startsWith("#path:")) {
                config.put("path", line.substring(6).trim());
            } else if (line.startsWith("#action:")) {
                config.put("action", line.substring(8).trim());
            }
            return null;
        }).when(configLineHandlerChain).handleLine(anyString(), anyMap());

        Configuration config = configurationReader.readConfiguration(tempFile.toString(), "config1");

        assertNotNull(config);
        assertEquals("dir", config.getMode());
        assertNull(config.getPath());
        assertNull(config.getAction());

        verify(configLineHandlerChain, times(1)).handleLine(anyString(), anyMap());

        Files.delete(tempFile);
    }

    @Test
    void readConfiguration_IncompleteConfig() throws IOException {
        String configContent = "#config1\n#mode: dir\n#path: /some/path";
        Path tempFile = Files.createTempFile("config", ".txt");
        Files.write(tempFile, configContent.getBytes());

        doAnswer(invocation -> {
            String line = invocation.getArgument(0);
            Map<String, String> config = invocation.getArgument(1);
            if (line.startsWith("#mode:")) {
                config.put("mode", line.substring(6).trim());
            } else if (line.startsWith("#path:")) {
                config.put("path", line.substring(6).trim());
            } else if (line.startsWith("#action:")) {
                config.put("action", line.substring(8).trim());
            }
            return null;
        }).when(configLineHandlerChain).handleLine(anyString(), anyMap());

        Configuration config = configurationReader.readConfiguration(tempFile.toString(), "config1");

        assertNotNull(config);
        assertEquals("dir", config.getMode());
        assertEquals("/some/path", config.getPath());
        assertNull(config.getAction());

        verify(configLineHandlerChain, times(2)).handleLine(anyString(), anyMap());

        Files.delete(tempFile);
    }

    @Test
    void readConfiguration_ConfigWithEmptyValues() throws IOException {
        String configContent = "#config1\n#mode: \n#path: \n#action: ";
        Path tempFile = Files.createTempFile("config", ".txt");
        Files.write(tempFile, configContent.getBytes());

        doAnswer(invocation -> {
            String line = invocation.getArgument(0);
            Map<String, String> config = invocation.getArgument(1);
            if (line.startsWith("#mode:")) {
                config.put("mode", line.substring(6).trim());
            } else if (line.startsWith("#path:")) {
                config.put("path", line.substring(6).trim());
            } else if (line.startsWith("#action:")) {
                config.put("action", line.substring(8).trim());
            }
            return null;
        }).when(configLineHandlerChain).handleLine(anyString(), anyMap());

        Configuration config = configurationReader.readConfiguration(tempFile.toString(), "config1");

        assertNotNull(config);
        assert(config.getMode().isEmpty());
        assert(config.getPath().isEmpty());
        assert(config.getAction().isEmpty());

        verify(configLineHandlerChain, times(3)).handleLine(anyString(), anyMap());

        Files.delete(tempFile);
    }
}