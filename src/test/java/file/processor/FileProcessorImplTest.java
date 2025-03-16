package file.processor;

import file.actionHandler.ActionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileProcessorImplTest {

    @Mock
    private ActionHandler actionHandlerChain;

    private FileProcessorImpl fileProcessor;

    @BeforeEach
    void setUp() {
        fileProcessor = new FileProcessorImpl(actionHandlerChain);
    }

    @Test
    void processFilesContent_singleFile_singleLine() {
        List<List<String>> fileLines = List.of(List.of("test line"));
        String action = "testAction";

        when(actionHandlerChain.handle(anyString(), anyInt(), anyString()))
                .thenReturn(Map.of("1", "processed"));

        Map<String, Map<String, String>> result = fileProcessor.processFilesContent(fileLines, action);

        assertEquals(1, result.size());
        assertEquals(Map.of("1", "processed"), result.get("1"));
        verify(actionHandlerChain).handle("test line", 1, action);
    }

    @Test
    void processFilesContent_multipleFiles_multipleLines() {
        List<List<String>> fileLines = List.of(
                List.of("line1 file1", "line2 file1"),
                List.of("line1 file2", "line2 file2", "line3 file2")
        );
        String action = "testAction";

        when(actionHandlerChain.handle("line1 file1", 1, action)).thenReturn(Map.of("1", "processed1-1"));
        when(actionHandlerChain.handle("line1 file2", 2, action)).thenReturn(Map.of("2", "processed2-1"));
        when(actionHandlerChain.handle("line2 file1", 1, action)).thenReturn(Map.of("1", "processed1-2"));
        when(actionHandlerChain.handle("line2 file2", 2, action)).thenReturn(Map.of("2", "processed2-2"));
        when(actionHandlerChain.handle("line3 file2", 2, action)).thenReturn(Map.of("2", "processed2-3"));
        when(actionHandlerChain.handle("", 1, action)).thenReturn(Map.of("1", "processed1-3"));

        Map<String, Map<String, String>> result = fileProcessor.processFilesContent(fileLines, action);

        assertEquals(3, result.size());
        assertEquals(Map.of("1", "processed1-1", "2", "processed2-1"), result.get("1"));
        assertEquals(Map.of("1", "processed1-2", "2", "processed2-2"), result.get("2"));
        assertEquals(Map.of("1", "processed1-3", "2", "processed2-3"), result.get("3"));

        verify(actionHandlerChain).handle("line1 file1", 1, action);
        verify(actionHandlerChain).handle("line1 file2", 2, action);
        verify(actionHandlerChain).handle("line2 file1", 1, action);
        verify(actionHandlerChain).handle("line2 file2", 2, action);
        verify(actionHandlerChain).handle("line3 file2", 2, action);
        verify(actionHandlerChain).handle("", 1, action);
    }

    @Test
    void processFilesContent_noFiles() {
        List<List<String>> fileLines = new ArrayList<>();
        String action = "testAction";

        Map<String, Map<String, String>> result = fileProcessor.processFilesContent(fileLines, action);

        assertTrue(result.isEmpty());
        verify(actionHandlerChain, never()).handle(anyString(), anyInt(), anyString());
    }
}