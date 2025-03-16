package config.reader;

import config.Configuration;

import java.io.IOException;

public interface ConfigurationReader {
    Configuration readConfiguration(String configFile, String configId) throws IOException;
}
