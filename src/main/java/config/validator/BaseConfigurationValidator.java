package config.validator;

import config.configuration.Configuration;
import java.util.Set;

/**
 * Абстрактный базовый класс для валидаторов конфигураций.
 * Предоставляет общие поля и метод для валидации.
 */
public abstract class BaseConfigurationValidator {

    /**
     * Множество допустимых действий (actions).
     */
    protected final Set<String> allowedActions;

    /**
     * Конструктор для создания экземпляра BaseConfigurationValidator.
     *
     * @param allowedActions Множество допустимых действий.
     */
    public BaseConfigurationValidator(Set<String> allowedActions) {
        this.allowedActions = allowedActions;
    }

    /**
     * Абстрактный метод для валидации конфигурации.
     *
     * @param configuration Конфигурация для валидации.
     * @throws IllegalArgumentException Если конфигурация не проходит валидацию.
     */
    public abstract void validate(Configuration configuration);
}