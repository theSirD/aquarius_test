package file.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderImplTest {

    private FileReaderImpl fileReader;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileReader = new FileReaderImpl();
    }

    @Test
    void getFiles_dirMode_validDirectory() throws IOException {
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));
        Files.createDirectory(tempDir.resolve("subdir"));

        List<File> files = fileReader.getFiles("dir", tempDir.toString());

        assertEquals(2, files.size());
        assertEquals("file1.txt", files.get(0).getName());
        assertEquals("file2.txt", files.get(1).getName());
    }

    @Test
    void getFiles_dirMode_invalidDirectory() {
        List<File> files = fileReader.getFiles("dir", "/invalid/path");
        assertTrue(files.isEmpty());
    }

    @Test
    void getFiles_filesMode_validFiles() throws IOException {
        File file1 = tempDir.resolve("file1.txt").toFile();
        File file2 = tempDir.resolve("file2.txt").toFile();
        assertTrue(file1.createNewFile());
        assertTrue(file2.createNewFile());

        List<File> files = fileReader.getFiles("files", file1.getAbsolutePath() + "," + file2.getAbsolutePath());

        assertEquals(2, files.size());
        assertEquals("file1.txt", files.get(0).getName());
        assertEquals("file2.txt", files.get(1).getName());
    }

    @Test
    void getFiles_filesMode_invalidFiles() {
        List<File> files = fileReader.getFiles("files", "/invalid/file1.txt,/invalid/file2.txt");
        assertTrue(files.isEmpty());
    }

    @Test
    void getFiles_filesMode_mixedValidAndInvalidFiles() throws IOException {
        File validFile = tempDir.resolve("validFile.txt").toFile();
        assertTrue(validFile.createNewFile());

        List<File> files = fileReader.getFiles("files", validFile + ",/invalid/file.txt");

        assertEquals(1, files.size());
        assertEquals("validFile.txt", files.get(0).getName());
    }

    @Test
    void getFilesContent_validFiles() throws IOException {
        File file1 = tempDir.resolve("file1.txt").toFile();
        File file2 = tempDir.resolve("file2.txt").toFile();
        assertTrue(file1.createNewFile());
        assertTrue(file2.createNewFile());
        try (FileWriter writer1 = new FileWriter(file1);
             FileWriter writer2 = new FileWriter(file2)) {
            writer1.write("line1\nline2");
            writer2.write("line3");
        }

        List<List<String>> fileContents = fileReader.getFilesContent(List.of(file1, file2));

        assertEquals(2, fileContents.size());
        assertEquals(List.of("line1", "line2"), fileContents.get(0));
        assertEquals(List.of("line3"), fileContents.get(1));
    }

    @Test
    void getFilesContent_emptyFiles() throws IOException {
        File file1 = tempDir.resolve("file1.txt").toFile();
        assertTrue(file1.createNewFile());

        List<List<String>> fileContents = fileReader.getFilesContent(List.of(file1));

        assertEquals(1, fileContents.size());
        assertTrue(fileContents.get(0).isEmpty());
    }

    @Test
    void getFilesContent_fileNotFound() {
        File nonExistentFile = new File("/invalid/file.txt");
        assertThrows(IOException.class, () -> fileReader.getFilesContent(List.of(nonExistentFile)));
    }
}