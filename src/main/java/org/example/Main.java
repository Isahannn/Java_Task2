package org.example;

import org.example.entity.Point;
import org.example.entity.StraightLineEntity;
import org.example.service.StraightLineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        StraightLineService lineService = new StraightLineService();


        StraightLineEntity line1 = new StraightLineEntity(1, -1, -2);
        StraightLineEntity line2 = new StraightLineEntity(2, -2, -4);
        StraightLineEntity line3 = new StraightLineEntity(1, 2, -3);


        Point intersectionWithAxes = lineService.getIntersectionWithAxes(line1);
        System.out.println("Intersection of line1 with axes: " + intersectionWithAxes);


        Point intersection = lineService.getIntersection(line1, line3);
        System.out.println("Intersection of line1 and line3: " + intersection);


        boolean isParallel = lineService.isParallel(line1, line2);
        System.out.println("Are line1 and line2 parallel? " + isParallel);


        List<StraightLineEntity> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        Map<String, List<StraightLineEntity>> parallelGroups = lineService.groupParallelLines(lines);
        System.out.println("Parallel groups:");
        parallelGroups.forEach((key, value) -> System.out.println("Group " + key + ": " + value));
    }
}