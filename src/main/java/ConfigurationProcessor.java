import config.Configuration;
import config.reader.ConfigurationReader;
import config.reader.ConfigurationReaderImpl;
import file.processor.FileProcessor;
import file.processor.FileProcessorImpl;
import file.reader.FileReaderImpl;
import json.JsonGenerator;
import json.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationProcessor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Использование: java ConfigurationProcessor <конфигурационный_файл> <номер_конфигурации>");
            System.exit(1);
        }

        String configFile = args[0];
        String configId = args[1];

        try {
            ConfigurationReader configurationReader = new ConfigurationReaderImpl();
            Configuration config = configurationReader.readConfiguration(configFile, configId);
            if (config == null) {
                System.err.println("Конфигурация с номером " + configId + " не найдена.");
                System.exit(1);
            }

            config.validate();

            FileReaderImpl fileReader = new FileReaderImpl();
            FileProcessor contentProcessor = new FileProcessorImpl();

            List<File> files = fileReader.getFiles(config.getMode(), config.getPath());
            List<List<String>> fileLines = fileReader.getFilesContent(files);

            Map<String, Map<String, String>> result = contentProcessor.processFilesContent(fileLines, config.getAction());

            Map<String, Object> outputData = new HashMap<>();
            outputData.put("configFile", configFile);
            outputData.put("configurationID", configId);
            outputData.put("configurationData", config);
            outputData.put("out", result);

            String jsonOutput = JsonGenerator.generateJson(outputData);
            String outputFilePath = JsonWriter.saveJson(jsonOutput, "output.json");
            System.out.println("Результат сохранен в: " + outputFilePath);

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Ошибка при обработке конфигурации: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}