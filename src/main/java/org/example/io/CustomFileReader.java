package org.example.io;

import org.example.exception.StraightLineEntityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

    private static final Logger logger = LogManager.getLogger(CustomFileReader.class);

    public List<String> readLinesFromFile(String filename) throws StraightLineEntityException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: {}", filename, e);
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.FILE_OPERATION,
                    "Ошибка при чтении файла: " + filename,
                    e
            );
        }
        return lines;
    }
}
