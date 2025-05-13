package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.facade.StraightLineFacade;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DEFAULT_DATA_FILE = "data/straight_lines.txt";

    public static void main(String[] args) {
        try {
            StraightLineFacade facade = new StraightLineFacade();
            List<StraightLineEntity> lines = facade.loadStraightLines(DEFAULT_DATA_FILE);

            logger.info("Successfully loaded {} lines", lines.size());
            lines.forEach(line -> logger.debug("Loaded line: {}", line));

        } catch (StraightLineEntityException e) {
            logger.error("Error loading lines: {}", e.getMessage());
            printFileLocations(e);
        }
    }

    private static void printFileLocations(StraightLineEntityException e) {
        logger.info("\nPlease ensure the file exists in one of these locations:");
        logger.info("1. Project directory: {}",
                Paths.get("").toAbsolutePath() + "/data/straight_lines.txt");
        logger.info("2. Resources directory: src/main/resources/straight_lines.txt");

        logger.info("\nExample file content:");
        logger.info("1.0,2.0,3.0,0.5,0.5,0.5");
        logger.info("4.0,5.0,6.0,1.0,0.0,0.0");

        if (e.getCause() != null) {
            logger.debug("Root cause:", e);
        }
    }
}