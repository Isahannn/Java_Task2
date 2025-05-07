package org.example.creator.Impl;

import org.example.entity.StraightLineEntity;

import java.util.Optional;

public interface StraightLineFactory {
    Optional<StraightLineEntity> create2D(double px, double py, double dx, double dy);
    Optional<StraightLineEntity> create3D(double px, double py, double pz, double dx, double dy, double dz);
}
