package file.processor;

import file.actionHandler.ActionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация интерфейса FileProcessor для обработки содержимого файлов.
 * Использует цепочку обработчиков действий для выполнения операций над строками.
 */
public class FileProcessorImpl implements FileProcessor {

    /**
     * Цепочка обработчиков действий.
     */
    private final ActionHandler _actionHandlerChain;

    /**
     * Конструктор для создания экземпляра FileProcessorImpl.
     *
     * @param actionHandlerChain Цепочка обработчиков действий.
     */
    public FileProcessorImpl(ActionHandler actionHandlerChain) {
        _actionHandlerChain = actionHandlerChain;
    }

    /**
     * Обрабатывает содержимое файлов в соответствии с заданным действием.
     *
     * @param fileLines Список строк каждого файла.
     * @param action    Действие, которое необходимо выполнить.
     * @return Карта, содержащая результаты обработки, где ключ - номер строки, а значение - карта результатов для каждого файла.
     */
    public Map<String, Map<String, String>> processFilesContent(List<List<String>> fileLines, String action) {
        Map<String, Map<String, String>> out = new HashMap<>();
        int maxLines = fileLines.stream().mapToInt(List::size).max().orElse(0);

        for (int i = 0; i < maxLines; i++) {
            Map<String, String> lineData = new HashMap<>();
            for (int j = 0; j < fileLines.size(); j++) {
                List<String> lines = fileLines.get(j);
                String line = i < lines.size() ? lines.get(i) : "";
                lineData.putAll(_actionHandlerChain.handle(line, j + 1, action));
            }
            out.put(String.valueOf(i + 1), lineData);
        }
        return out;
    }
}