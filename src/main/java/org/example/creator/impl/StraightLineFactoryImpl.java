package org.example.creator.impl;

import org.example.creator.StraightLineFactory;
import org.example.entity.StraightLineEntity;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.Optional;

public class StraightLineFactoryImpl implements StraightLineFactory {
    private final StraightLineValidatorImpl validator;

    public StraightLineFactoryImpl() {
        this.validator = new StraightLineValidatorImpl();
    }

    public StraightLineFactoryImpl(StraightLineValidatorImpl validator) {
        this.validator = validator;
    }

    @Override
    public Optional<StraightLineEntity> create2D(double px, double py, double dx, double dy) {
        double[] coords = {px, py, 0, dx, dy, 0};
        return validator.isValid(coords)
                ? Optional.of(new StraightLineEntity(px, py, dx, dy))
                : Optional.empty();
    }

    @Override
    public Optional<StraightLineEntity> create3D(double px, double py, double pz, double dx, double dy, double dz) {
        double[] coords = {px, py, pz, dx, dy, dz};
        return validator.isValid(coords)
                ? Optional.of(new StraightLineEntity(px, py, pz, dx, dy, dz))
                : Optional.empty();
    }
    }