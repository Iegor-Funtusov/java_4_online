package ua.com.alevel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileNIO {
//
//    Path path;
//    Paths paths;
//    Files files;

    public void create(String filePath) throws IOException {
//        File file = new File(filePath); // io
        Path path = Paths.get(filePath); // nio
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public void createDir(String filePath) throws IOException {
//        File file = new File(filePath); // io
        Path path = Paths.get(filePath); // nio
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    public void createDirs(String filePath) throws IOException {
//        File file = new File(filePath); // io
        Path path = Paths.get(filePath); // nio
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    public void remove(String filePath) throws IOException {
        Path path = Paths.get(filePath); // nio
        if (!Files.exists(path)) {
            Files.delete(path);
            Files.deleteIfExists(path);
        }
    }

    public void write(String filePath, String text) throws IOException {
        Path path = Paths.get(filePath); // nio
        Files.write(path, text.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    public void read(String filePath) throws IOException {
        Path path = Paths.get(filePath); // nio
        Stream<String> lines = Files.lines(path);
        lines.forEach(text -> {
            System.out.println("text = " + text);
        });
    }
}
