package config.validator;

import config.configuration.Configuration;

import java.util.Set;

public class ConfigurationValidator extends BaseConfigurationValidator {

    public ConfigurationValidator(Set<String> allowedActions) {
        super(allowedActions);
    }

    @Override
    public void validate(Configuration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Конфигурация не может быть null");
        }

        String mode = configuration.getMode();
        String path = configuration.getPath();
        String action = configuration.getAction();

        if (mode == null || (!mode.equals("dir") && !mode.equals("files"))) {
            throw new IllegalArgumentException("Неверный режим (mode): " + mode);
        }

        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверный путь (path): " + path);
        }

        if (mode.equals("dir") && path.trim().split(",").length > 1) {
            throw new IllegalArgumentException("Для режима 'dir' в поле 'path' должна быть указана только одна директория");
        }

        if (action == null || !allowedActions.contains(action)) {
            throw new IllegalArgumentException("Неверное действие (action): " + action);
        }
    }
}