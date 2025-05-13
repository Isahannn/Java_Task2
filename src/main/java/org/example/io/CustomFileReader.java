package org.example.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger(CustomFileReader.class);
    private static final String RESOURCES_PREFIX = "/";

    public List<String> readLinesFromFile(String relativePath) throws IOException {
        Objects.requireNonNull(relativePath, "File path cannot be null");

        Path filePath = Paths.get(relativePath).toAbsolutePath();
        logger.debug("Attempting to read file from: {}", filePath);

        if (Files.exists(filePath)) {
            logger.info("Reading file from filesystem: {}", filePath);
            return readFileFromFilesystem(filePath);
        }

        logger.debug("File not found in filesystem, trying resources...");
        return readFileFromResources(relativePath);
    }

    private List<String> readFileFromFilesystem(Path filePath) throws IOException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            logger.error("Failed to read file from filesystem: {}", filePath, e);
            throw new IOException("Failed to read file: " + filePath, e);
        }
    }

    private List<String> readFileFromResources(String resourcePath) throws IOException {
        String normalizedPath = resourcePath.startsWith(RESOURCES_PREFIX)
                ? resourcePath
                : RESOURCES_PREFIX + resourcePath;

        InputStream inputStream = getClass().getResourceAsStream(normalizedPath);
        if (inputStream == null) {
            String errorMsg = "File not found in filesystem or resources: " + resourcePath;
            logger.error(errorMsg);
            throw new FileNotFoundException(errorMsg);
        }

        try {
            return readFromStream(inputStream);
        } catch (IOException e) {
            logger.error("Failed to read resource file: {}", resourcePath, e);
            throw new IOException("Failed to read resource file: " + resourcePath, e);
        }
    }

    private List<String> readFromStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}