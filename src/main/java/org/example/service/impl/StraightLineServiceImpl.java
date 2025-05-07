package org.example.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Point;
import org.example.entity.StraightLineEntity;
import org.example.service.StraightLineArithmeticService;
import org.example.service.StraightLineService;

import java.util.*;

public class StraightLineServiceImpl implements StraightLineService {
    private static final Logger logger = LogManager.getLogger();
    private final StraightLineArithmeticService arithmeticService = new StraightLineArithmeticServiceImpl();

    @Override
    public Point getIntersectionWithAxes(StraightLineEntity line) {
        double px = line.getPx();
        double py = line.getPy();
        double pz = line.getPz();
        double dx = line.getDx();
        double dy = line.getDy();
        double dz = line.getDz();

        // Calculate intersection with XY plane (z=0)
        if (dz != 0) {
            double t = -pz / dz;
            return new Point(px + dx * t, py + dy * t, 0.0); // Ensure the result is Double
        }
        // Calculate intersection with XZ plane (y=0)
        else if (dy != 0) {
            double t = -py / dy;
            return new Point(px + dx * t, 0.0, pz + dz * t); // Ensure the result is Double
        }
        // Calculate intersection with YZ plane (x=0)
        else if (dx != 0) {
            double t = -px / dx;
            return new Point(0.0, py + dy * t, pz + dz * t); // Ensure the result is Double
        }
        // Line doesn't intersect any axis
        return null;
    }

    @Override
    public Point getIntersection(StraightLineEntity line1, StraightLineEntity line2) {
        // Implementation of line-line intersection in 3D space
        // This is a simplified version - actual implementation may vary based on requirements
        double dx1 = line1.getDx();
        double dy1 = line1.getDy();
        double dz1 = line1.getDz();

        double dx2 = line2.getDx();
        double dy2 = line2.getDy();
        double dz2 = line2.getDz();

        // Check if lines are parallel
        if (isParallel(line1, line2)) {
            return null;
        }

        // Solve system of equations to find intersection point
        // This is a simplified approach - may need more robust solution
        try {
            double t = ((line2.getPx() - line1.getPx()) * dy2 - (line2.getPy() - line1.getPy()) * dx2) /
                    (dx1 * dy2 - dy1 * dx2);

            double x = line1.getPx() + dx1 * t;
            double y = line1.getPy() + dy1 * t;
            double z = line1.getPz() + dz1 * t;

            return new Point(x, y, z);
        } catch (ArithmeticException e) {
            logger.error("Error calculating intersection", e);
            return null;
        }
    }

    @Override
    public boolean isParallel(StraightLineEntity line1, StraightLineEntity line2) {
        double dx1 = line1.getDx();
        double dy1 = line1.getDy();
        double dz1 = line1.getDz();

        double dx2 = line2.getDx();
        double dy2 = line2.getDy();
        double dz2 = line2.getDz();

        // Check if direction vectors are scalar multiples
        double ratioX = dx1 != 0 ? dx2 / dx1 : 0.0;
        double ratioY = dy1 != 0 ? dy2 / dy1 : 0.0;
        double ratioZ = dz1 != 0 ? dz2 / dz1 : 0.0;

        // Account for zero components
        if ((dx1 == 0 && dx2 != 0) || (dy1 == 0 && dy2 != 0) || (dz1 == 0 && dz2 != 0)) {
            return false;
        }

        // Check if all non-zero ratios are equal (within some epsilon for floating point)
        double epsilon = 1e-10;
        return Math.abs(ratioX - ratioY) < epsilon && Math.abs(ratioY - ratioZ) < epsilon;
    }

    @Override
    public Map<String, List<StraightLineEntity>> groupParallelLines(List<StraightLineEntity> lines) {
        Map<String, List<StraightLineEntity>> groups = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            StraightLineEntity line1 = lines.get(i);
            boolean added = false;

            // Check existing groups
            for (Map.Entry<String, List<StraightLineEntity>> entry : groups.entrySet()) {
                if (isParallel(line1, entry.getValue().get(0))) {
                    entry.getValue().add(line1);
                    added = true;
                    break;
                }
            }

            // Create new group if not parallel to any existing
            if (!added) {
                String groupId = "group_" + (groups.size() + 1);
                List<StraightLineEntity> newGroup = new ArrayList<>();
                newGroup.add(line1);
                groups.put(groupId, newGroup);
            }
        }

        return groups;
    }

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
