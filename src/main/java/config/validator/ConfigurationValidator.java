package config.validator;

import config.configuration.Configuration;

import java.util.Set;

/**
 * Валидатор конфигураций, проверяющий корректность полей конфигурации.
 */
public class ConfigurationValidator extends BaseConfigurationValidator {

    /**
     * Конструктор для создания экземпляра ConfigurationValidator.
     *
     * @param allowedActions Множество допустимых действий.
     */
    public ConfigurationValidator(Set<String> allowedActions) {
        super(allowedActions);
    }

    /**
     * Валидирует конфигурацию, проверяя корректность полей "mode", "path" и "action".
     *
     * @param configuration Конфигурация для валидации.
     * @throws IllegalArgumentException Если конфигурация не проходит валидацию.
     */
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