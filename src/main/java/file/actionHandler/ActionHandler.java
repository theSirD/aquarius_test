package file.actionHandler;

import java.util.Map;

public interface ActionHandler {
    Map<String, String> handle(String line, int fileNumber, String action);
    ActionHandler setNext(ActionHandler next);
}