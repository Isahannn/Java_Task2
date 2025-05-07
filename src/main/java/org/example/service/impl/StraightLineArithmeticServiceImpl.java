package org.example.service.impl;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.service.StraightLineArithmeticService;

public class StraightLineArithmeticServiceImpl implements StraightLineArithmeticService {

    @Override
    public StraightLineEntity add(StraightLineEntity l1, StraightLineEntity l2) {
        return new StraightLineEntity(
                l1.getPx() + l2.getPx(),
                l1.getPy() + l2.getPy(),
                l1.getPz() + l2.getPz(),
                l1.getDx() + l2.getDx(),
                l1.getDy() + l2.getDy(),
                l1.getDz() + l2.getDz()
        );
    }

    @Override
    public StraightLineEntity subtract(StraightLineEntity l1, StraightLineEntity l2) {
        return new StraightLineEntity(
                l1.getPx() - l2.getPx(),
                l1.getPy() - l2.getPy(),
                l1.getPz() - l2.getPz(),
                l1.getDx() - l2.getDx(),
                l1.getDy() - l2.getDy(),
                l1.getDz() - l2.getDz()
        );
    }

    @Override
    public StraightLineEntity multiplyByScalar(StraightLineEntity line, double scalar) {
        return new StraightLineEntity(
                line.getPx() * scalar,
                line.getPy() * scalar,
                line.getPz() * scalar,
                line.getDx() * scalar,
                line.getDy() * scalar,
                line.getDz() * scalar
        );
    }

    @Override
    public StraightLineEntity divideByScalar(StraightLineEntity line, double scalar) throws StraightLineEntityException {
        if (scalar == 0) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.DIVISION_BY_ZERO,
                    "Cannot divide by zero"
            );
        }
        return new StraightLineEntity(
                line.getPx() / scalar,
                line.getPy() / scalar,
                line.getPz() / scalar,
                line.getDx() / scalar,
                line.getDy() / scalar,
                line.getDz() / scalar
        );
    }
}
