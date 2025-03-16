package config.lineHandler;
import java.util.Map;

public abstract class BaseConfigLineHandler implements ConfigLineHandler {
    protected ConfigLineHandler nextHandler;

    @Override
    public ConfigLineHandler setNext(ConfigLineHandler next) {
        this.nextHandler = next;
        return next;
    }

    @Override
    public void handleLine(String line, Map<String, String> config) {
        if (nextHandler != null) {
            nextHandler.handleLine(line, config);
        }
    }

    protected abstract boolean canHandle(String action);
}