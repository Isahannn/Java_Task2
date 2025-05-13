package org.example.service.impl;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.creator.StraightLineFactory;
import org.example.creator.impl.StraightLineFactoryImpl;
import org.example.service.StraightLineArithmeticService;

public class StraightLineArithmeticServiceImpl implements StraightLineArithmeticService {
    private final StraightLineFactory factory = new StraightLineFactoryImpl();

    @Override
    public StraightLineEntity add(StraightLineEntity l1, StraightLineEntity l2) throws StraightLineEntityException {
        validateInputLines(l1, l2);
        return createValidatedLine(
                l1.getPx() + l2.getPx(),
                l1.getPy() + l2.getPy(),
                l1.getPz() + l2.getPz(),
                l1.getDx() + l2.getDx(),
                l1.getDy() + l2.getDy(),
                l1.getDz() + l2.getDz()
        );
    }

    @Override
    public StraightLineEntity subtract(StraightLineEntity l1, StraightLineEntity l2) throws StraightLineEntityException {
        validateInputLines(l1, l2);
        return createValidatedLine(
                l1.getPx() - l2.getPx(),
                l1.getPy() - l2.getPy(),
                l1.getPz() - l2.getPz(),
                l1.getDx() - l2.getDx(),
                l1.getDy() - l2.getDy(),
                l1.getDz() - l2.getDz()
        );
    }

    @Override
    public StraightLineEntity multiplyByScalar(StraightLineEntity line, double scalar) throws StraightLineEntityException {
        validateInputLine(line);
        return createValidatedLine(
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
                    "Division by zero is not allowed"
            );
        }
        validateInputLine(line);
        return createValidatedLine(
                line.getPx() / scalar,
                line.getPy() / scalar,
                line.getPz() / scalar,
                line.getDx() / scalar,
                line.getDy() / scalar,
                line.getDz() / scalar
        );
    }

    private void validateInputLine(StraightLineEntity line) throws StraightLineEntityException {
        if (line == null) {
            throw new StraightLineEntityException(
                    StraightLineEntityException.ErrorType.NULL_LINE,
                    "Input line cannot be null"
            );
        }
    }

    private void validateInputLines(StraightLineEntity l1, StraightLineEntity l2) throws StraightLineEntityException {
        validateInputLine(l1);
        validateInputLine(l2);
    }

    private StraightLineEntity createValidatedLine(double px, double py, double pz,
                                                   double dx, double dy, double dz)
            throws StraightLineEntityException {
        return factory.create3D(px, py, pz, dx, dy, dz).orElseThrow(() ->
                new StraightLineEntityException(
                        StraightLineEntityException.ErrorType.INVALID_LINE_DATA,
                        "Resulting line parameters are invalid"
                )
        );
    }
}