import config.configuration.Configuration;
import config.lineHandler.ActionLineHandler;
import config.lineHandler.ModeLineHandler;
import config.lineHandler.PathLineHandler;
import config.reader.ConfigurationReader;
import config.reader.ConfigurationReaderImpl;
import config.validator.ConfigurationValidator;
import file.actionHandler.ActionHandler;
import file.actionHandler.CountActionHandler;
import file.actionHandler.ReplaceActionHandler;
import file.actionHandler.StringActionHandler;
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
import java.util.Set;

public class ConfigurationProcessor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Использование: java ConfigurationProcessor <конфигурационный_файл> <номер_конфигурации>");
            System.exit(1);
        }

        String configFile = args[0];
        String configId = args[1];

        try {
            ModeLineHandler modeHandler = new ModeLineHandler();
            PathLineHandler pathHandler = new PathLineHandler();
            ActionLineHandler actionHandler = new ActionLineHandler();
            modeHandler.setNext(pathHandler).setNext(actionHandler);
            ConfigurationReader configurationReader = new ConfigurationReaderImpl(modeHandler);

            Configuration config = configurationReader.readConfiguration(configFile, configId);
            if (config == null) {
                System.err.println("Конфигурация с номером " + configId + " не найдена.");
                System.exit(1);
            }

            Set<String> allowedActions = Set.of("string", "count", "replace");
            ConfigurationValidator validator = new ConfigurationValidator(allowedActions);
            validator.validate(config);

            FileReaderImpl fileReader = new FileReaderImpl();

            ActionHandler stringHandler = new StringActionHandler();
            ActionHandler countHandler = new CountActionHandler();
            ActionHandler replaceHandler = new ReplaceActionHandler();
            stringHandler.setNext(countHandler).setNext(replaceHandler);
            FileProcessor contentProcessor = new FileProcessorImpl(stringHandler);

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