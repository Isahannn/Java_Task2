package org.example.parser;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StraightLineParser {
    private final StraightLineValidatorImpl validator;

    public StraightLineParser() {
        this.validator = new StraightLineValidatorImpl();
    }

    public StraightLineParser(StraightLineValidatorImpl validator) {
        this.validator = Objects.requireNonNull(validator);
    }

    public List<StraightLineEntity> parseLines(List<String> lines) throws StraightLineEntityException {
        Objects.requireNonNull(lines, "Input lines list cannot be null");
        List<StraightLineEntity> lineEntities = new ArrayList<>();

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) continue;

            String[] parts = trimmedLine.split(",");
            if (parts.length != 6) {
                throw new StraightLineEntityException(
                        StraightLineEntityException.ErrorType.INVALID_LINE_DATA,
                        String.format("Invalid data format at line %d: %s (expected 6 values, got %d)",
                                lineNumber, line, parts.length)
                );
            }

            double[] coords = new double[6];
            try {
                for (int i = 0; i < 6; i++) {
                    coords[i] = Double.parseDouble(parts[i].trim());
                }

                if (!validator.isValid(coords)) {
                    throw new StraightLineEntityException(
                            StraightLineEntityException.ErrorType.INVALID_LINE_DATA,
                            String.format("Invalid line data at line %d: %s (direction vector cannot be zero)",
                                    lineNumber, line)
                    );
                }

                StraightLineEntity entity = new StraightLineEntity(
                        coords[0], coords[1], coords[2],
                        coords[3], coords[4], coords[5]
                );

                lineEntities.add(entity);

            } catch (NumberFormatException e) {
                throw new StraightLineEntityException(
                        StraightLineEntityException.ErrorType.PARSING_ERROR,
                        String.format("Failed to parse numbers at line %d: %s", lineNumber, line),
                        e
                );
            }
        }

        if (lineEntities.isEmpty()) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.EMPTY_INPUT,
                    "No valid lines found in input"
            );
        }

        return Collections.unmodifiableList(lineEntities);
    }
}