package config.configuration;

/**
 * Класс, представляющий конфигурацию приложения.
 * Наследуется от BaseConfiguration и содержит поля mode, path и action.
 */
public class Configuration extends BaseConfiguration {
    /**
     * Конструктор для создания экземпляра Configuration.
     *
     * @param mode   Режим работы конфигурации.
     * @param path   Путь к файлам или директориям.
     * @param action Действие, выполняемое конфигурацией.
     */
    public Configuration(String mode, String path, String action) {
        super(mode, path, action);
    }
}