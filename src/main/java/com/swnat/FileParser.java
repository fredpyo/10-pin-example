package com.swnat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileParser {

    public List<String> readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> fileContents = Files.readAllLines(path);
        return fileContents;
    }

}
