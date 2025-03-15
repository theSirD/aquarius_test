package config;

public class Configuration {
    private final String mode;
    private final String path;
    private final String action;

    public Configuration(String mode, String path, String action) {
        this.mode = mode;
        this.path = path;
        this.action = action;
    }

    public String getMode() {
        return mode;
    }

    public String getPath() {
        return path;
    }

    public String getAction() {
        return action;
    }

    public void validate() {
        if (mode == null || (!mode.equals("dir") && !mode.equals("files"))) {
            throw new IllegalArgumentException("Неверный режим (mode): " + mode);
        }

        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверный путь (path): " + path);
        }

        if (action == null || (!action.equals("string") && !action.equals("count") && !action.equals("replace"))) {
            throw new IllegalArgumentException("Неверное действие (action): " + action);
        }
    }
}