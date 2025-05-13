package org.example.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.StraightLineEntityException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger();

    public List<String> readLinesFromFile(String filename) throws StraightLineEntityException {
        Path path = Paths.get(filename);
        try {
            if (Files.exists(path)) {
                logger.info("Reading file from filesystem: {}", path);
                return Files.lines(path, StandardCharsets.UTF_8)
                        .map(String::trim)
                        .collect(Collectors.toList());
            }

            logger.info("File not found on filesystem, trying as resource: {}", filename);
            InputStream inputStream = getClass().getResourceAsStream("/" + filename);
            if (inputStream == null) {
                throw new StraightLineEntityException(
                        StraightLineEntityException.ErrorType.FILE_OPERATION,
                        "File not found: " + filename
                );
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                return reader.lines()
                        .map(String::trim)
                        .collect(Collectors.toList());
            }

        } catch (IOException | NullPointerException e) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.FILE_OPERATION,
                    "Failed to read file: " + filename,
                    e
            );
        }
    }
}
