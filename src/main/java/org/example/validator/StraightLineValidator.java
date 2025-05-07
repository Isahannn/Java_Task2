package org.example.validator;

import org.example.entity.StraightLineEntity;

public interface StraightLineValidator {
    boolean validate(StraightLineEntity line);
    boolean validateDirection(double dx, double dy, double dz);
    boolean validatePoint(double px, double py, double pz);
}
