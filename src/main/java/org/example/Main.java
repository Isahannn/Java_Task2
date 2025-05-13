package org.example;

import org.example.entity.Point;
import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.example.facade.StraightLineFacade;
import org.example.service.StraightLineService;

import java.util.List;
import java.util.Map;



public class Main {
    public static final String DATA_FILE = "data/straight_lines.txt";
    public static void main(String[] args) {
        StraightLineFacade facade = new StraightLineFacade();

        List<StraightLineEntity> lines;
        try {
            lines = facade.loadStraightLines("straight_lines.txt");
        } catch (StraightLineEntityException e) {
            System.out.println("Error loading lines: " + e.getMessage());
            return;
        }

        if (lines == null || lines.isEmpty()) {
            System.out.println("No lines to process.");
            return;
        }

        StraightLineService lineService = facade.getLineService();

        StraightLineEntity line1 = lines.get(0);
        StraightLineEntity line2 = lines.size() > 1 ? lines.get(1) : null;
        StraightLineEntity line3 = lines.size() > 2 ? lines.get(2) : null;

        try {
            Point intersectionWithAxes = lineService.getIntersectionWithAxes(line1);
            System.out.println("Intersection of line1 with axes: " + intersectionWithAxes);

            if (line3 != null) {
                Point intersection = lineService.getIntersection(line1, line3);
                System.out.println("Intersection of line1 and line3: " + intersection);
            }

            if (line2 != null) {
                boolean isParallel = lineService.isParallel(line1, line2);
                System.out.println("Are line1 and line2 parallel? " + isParallel);
            }

            Map<String, List<StraightLineEntity>> parallelGroups = lineService.groupParallelLines(lines);
            System.out.println("Parallel groups:");
            parallelGroups.forEach((key, value) -> System.out.println("Group " + key + ": " + value));

        } catch (Exception e) {
            System.out.println("Error during calculations: " + e.getMessage());
        }
    }
}