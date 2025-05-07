package org.example.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.StraightLineEntity;
import org.example.service.StraightLineService;

public class StraightLineServiceImpl implements StraightLineService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public double magnitude(StraightLineEntity line) {
        return Math.sqrt(
                line.getDx() * line.getDx() +
                        line.getDy() * line.getDy() +
                        line.getDz() * line.getDz()
        );
    }

    @Override
    public boolean isZeroDirection(StraightLineEntity line) {
        return line.getDx() == 0 && line.getDy() == 0 && line.getDz() == 0;
    }
}
