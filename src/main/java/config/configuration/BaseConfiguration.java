package config.configuration;

public abstract class BaseConfiguration {
    protected final String mode;
    protected final String path;
    protected final String action;

    public BaseConfiguration(String mode, String path, String action) {
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
}