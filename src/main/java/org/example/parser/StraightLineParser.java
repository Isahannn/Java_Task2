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

    public StraightLineParser(StraightLineValidatorImpl validator) {
        this.validator = Objects.requireNonNull(validator, "Validator cannot be null");
    }

    public List<StraightLineEntity> parseLines(List<String> lines) throws StraightLineEntityException {
        Objects.requireNonNull(lines, "Input lines list cannot be null");
        List<StraightLineEntity> straightLines = new ArrayList<>();

        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) continue;

            String[] parts = trimmedLine.split(",");
            if (parts.length != 6) {
                throw new StraightLineEntityException(StraightLineEntityException.ErrorType.INVALID_LINE_DATA,
                        "Invalid data format in line: " + line);
            }

            try {
                double px = Double.parseDouble(parts[0].trim());
                double py = Double.parseDouble(parts[1].trim());
                double pz = Double.parseDouble(parts[2].trim());
                double dx = Double.parseDouble(parts[3].trim());
                double dy = Double.parseDouble(parts[4].trim());
                double dz = Double.parseDouble(parts[5].trim());

                StraightLineEntity entity = new StraightLineEntity(px, py, pz, dx, dy, dz);

                if (!validator.validate(entity)) {
                    throw new StraightLineEntityException(StraightLineEntityException.ErrorType.VALIDATION_ERROR,
                            "Invalid straight line data in line: " + line);
                }
                straightLines.add(entity);
            } catch (NumberFormatException e) {
                throw new StraightLineEntityException(StraightLineEntityException.ErrorType.PARSING_ERROR,
                        "Failed to parse numbers in line: " + line, e);
            }
        }
        return Collections.unmodifiableList(straightLines);
    }
}