package org.example.validator.impl;

import org.example.validator.StraightLineValidator;

public class StraightLineValidatorImpl implements StraightLineValidator {

    @Override
    public boolean isValid(double[] coords) {
        if (coords == null || coords.length != 6) {
            return false;
        }

        for (double coord : coords) {
            if (Double.isNaN(coord) || Double.isInfinite(coord)) {
                return false;
            }
        }

        double dx = coords[3];
        double dy = coords[4];
        double dz = coords[5];

        return !(dx == 0 && dy == 0 && dz == 0);
    }
}