package org.example.service;

import org.example.entity.StraightLineEntity;

public interface StraightLineService {
    double magnitude(StraightLineEntity line);
    boolean isZeroDirection(StraightLineEntity line);
}
