package org.example.creator.impl;

import org.example.creator.StraightLineFactory;
import org.example.entity.StraightLineEntity;
import org.example.validator.impl.StraightLineValidatorImpl;

import java.util.Optional;

public class StraightLineFactoryImpl implements StraightLineFactory {

    @Override
    public Optional<StraightLineEntity> create2D(double px, double py, double dx, double dy) {
        StraightLineEntity line = new StraightLineEntity(px, py, dx, dy);
        return StraightLineValidatorImpl.isValid(line) ? Optional.of(line) : Optional.empty();
    }

    @Override
    public Optional<StraightLineEntity> create3D(double px, double py, double pz, double dx, double dy, double dz) {
        StraightLineEntity line = new StraightLineEntity(px, py, pz, dx, dy, dz);
        return StraightLineValidatorImpl.isValid(line) ? Optional.of(line) : Optional.empty();
    }
}
