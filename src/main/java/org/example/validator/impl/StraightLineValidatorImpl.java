package org.example.validator.impl;

import org.example.entity.StraightLineEntity;
import org.example.validator.StraightLineValidator;

public class StraightLineValidatorImpl implements StraightLineValidator {

    private static final double MIN_COORDINATE = -1000;
    private static final double MAX_COORDINATE = 1000;

    private static final double MIN_DIRECTION = -100;
    private static final double MAX_DIRECTION = 100;

    public static boolean isValid(StraightLineEntity line) {
        StraightLineValidatorImpl validator = new StraightLineValidatorImpl();
        return validator.validate(line);
    }

    @Override
    public boolean validate(StraightLineEntity line) {
        if (line == null) {
            return false;
        }

        return validatePoint(line.getPx(), line.getPy(), line.getPz()) &&
                validateDirection(line.getDx(), line.getDy(), line.getDz()) &&
                !isZeroVector(line.getDx(), line.getDy(), line.getDz());
    }

    @Override
    public boolean validatePoint(double px, double py, double pz) {
        return isInRange(px, MIN_COORDINATE, MAX_COORDINATE) &&
                isInRange(py, MIN_COORDINATE, MAX_COORDINATE) &&
                isInRange(pz, MIN_COORDINATE, MAX_COORDINATE);
    }

    @Override
    public boolean validateDirection(double dx, double dy, double dz) {
        return isInRange(dx, MIN_DIRECTION, MAX_DIRECTION) &&
                isInRange(dy, MIN_DIRECTION, MAX_DIRECTION) &&
                isInRange(dz, MIN_DIRECTION, MAX_DIRECTION);
    }

    private boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    private boolean isZeroVector(double dx, double dy, double dz) {
        return dx == 0 && dy == 0 && dz == 0;
    }
}
