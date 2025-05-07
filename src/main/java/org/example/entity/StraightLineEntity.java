package org.example.entity;

import java.util.Objects;

public class StraightLineEntity {
    private final double px;
    private final double py;
    private final double pz;
    private final double dx;
    private final double dy;
    private final double dz;

    public StraightLineEntity(double px, double py, double pz,
                              double dx, double dy, double dz) {
        this.px = px;
        this.py = py;
        this.pz = pz;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public StraightLineEntity(double px, double py, double dx, double dy) {
        this(px, py, 0, dx, dy, 0);
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public double getPz() {
        return pz;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getDz() {
        return dz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StraightLineEntity that = (StraightLineEntity) o;
        return Double.compare(that.px, px) == 0 &&
                Double.compare(that.py, py) == 0 &&
                Double.compare(that.pz, pz) == 0 &&
                Double.compare(that.dx, dx) == 0 &&
                Double.compare(that.dy, dy) == 0 &&
                Double.compare(that.dz, dz) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(px, py, pz, dx, dy, dz);
    }

    @Override
    public String toString() {
        return String.format("StraightLineEntity{point(%.2f, %.2f, %.2f), direction[%.2f, %.2f, %.2f]}",
                px, py, pz, dx, dy, dz);
    }
}