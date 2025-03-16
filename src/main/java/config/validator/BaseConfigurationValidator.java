package config.validator;

import config.configuration.Configuration;

import java.util.Set;

public abstract class BaseConfigurationValidator {

    protected final Set<String> allowedActions;

    public BaseConfigurationValidator(Set<String> allowedActions) {
        this.allowedActions = allowedActions;
    }

    public abstract void validate(Configuration configuration);
}