package org.example.parser;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

        try {
            List<StraightLineEntity> entities = lines.stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(this::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(validator::isValid)
                    .collect(Collectors.toList());

            if (entities.isEmpty()) {
                throw new StraightLineEntityException(
                        StraightLineEntityException.ErrorType.EMPTY_INPUT,
                        "No valid straight line entities found in input"
                );
            }

            return List.copyOf(entities); // unmodifiable

        } catch (Exception e) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.PARSING_ERROR,
                    "Failed to parse input lines: " + e.getMessage(),
                    e
            );
        }
    }

    private Optional<StraightLineEntity> parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) return Optional.empty();

        try {
            double[] coords = new double[6];
            for (int i = 0; i < 6; i++) {
                coords[i] = Double.parseDouble(parts[i].trim());
            }

            return Optional.of(new StraightLineEntity(
                    coords[0], coords[1], coords[2],
                    coords[3], coords[4], coords[5]
            ));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
