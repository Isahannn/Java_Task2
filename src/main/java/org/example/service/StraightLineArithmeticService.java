package org.example.service;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;

public interface StraightLineArithmeticService {
    StraightLineEntity add(StraightLineEntity l1, StraightLineEntity l2);
    StraightLineEntity subtract(StraightLineEntity l1, StraightLineEntity l2);
    StraightLineEntity multiplyByScalar(StraightLineEntity line, double scalar);
    StraightLineEntity divideByScalar(StraightLineEntity line, double scalar) throws StraightLineEntityException;
}
