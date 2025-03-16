package file.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<File> getFiles(String mode, String path);
    List<List<String>> getFilesContent(List<File> files) throws IOException;
}
