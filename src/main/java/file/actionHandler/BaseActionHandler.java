package file.actionHandler;

import java.util.Map;

public abstract class BaseActionHandler implements ActionHandler {

    protected ActionHandler nextHandler;

    @Override
    public ActionHandler setNext(ActionHandler next) {
        this.nextHandler = next;
        return next;
    }

    @Override
    public Map<String, String> handle(String line, int fileNumber, String action) {
        if (nextHandler != null) {
            return nextHandler.handle(line, fileNumber, action);
        }
        return null;
    }

    protected abstract boolean canHandle(String action);
}