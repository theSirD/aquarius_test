package config.configuration;

/**
 * Абстрактный базовый класс для конфигураций.
 * Предоставляет общие поля и методы для работы с конфигурациями.
 */
public abstract class BaseConfiguration {
    /**
     * Режим работы конфигурации (например, "dir" или "files").
     */
    protected final String mode;

    /**
     * Путь к файлам или директориям, обрабатываемым конфигурацией.
     */
    protected final String path;

    /**
     * Действие, выполняемое конфигурацией (например, "string", "count", "replace").
     */
    protected final String action;

    /**
     * Конструктор для создания экземпляра BaseConfiguration.
     *
     * @param mode   Режим работы конфигурации.
     * @param path   Путь к файлам или директориям.
     * @param action Действие, выполняемое конфигурацией.
     */
    public BaseConfiguration(String mode, String path, String action) {
        this.mode = mode;
        this.path = path;
        this.action = action;
    }

    /**
     * Возвращает режим работы конфигурации.
     *
     * @return Режим работы конфигурации.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Возвращает путь к файлам или директориям.
     *
     * @return Путь к файлам или директориям.
     */
    public String getPath() {
        return path;
    }

    /**
     * Возвращает действие, выполняемое конфигурацией.
     *
     * @return Действие, выполняемое конфигурацией.
     */
    public String getAction() {
        return action;
    }
}