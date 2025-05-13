package org.example.service;

import org.example.entity.Point;
import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;

import java.util.List;
import java.util.Map;

public interface StraightLineService {
    Point getIntersectionWithAxes(StraightLineEntity line) throws StraightLineEntityException;
    Point getIntersection(StraightLineEntity line1, StraightLineEntity line2) throws StraightLineEntityException;
    boolean isParallel(StraightLineEntity line1, StraightLineEntity line2);
    Map<String, List<StraightLineEntity>> groupParallelLines(List<StraightLineEntity> lines);
    double magnitude(StraightLineEntity line);
    boolean isZeroDirection(StraightLineEntity line);
}
