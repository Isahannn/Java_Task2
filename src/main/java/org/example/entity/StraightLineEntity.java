package org.example.entity;

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
        return px == that.px &&
                py == that.py &&
                pz == that.pz &&
                dx == that.dx &&
                dy == that.dy &&
                dz == that.dz;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(px);
        result = 31 * result + Double.hashCode(py);
        result = 31 * result + Double.hashCode(pz);
        result = 31 * result + Double.hashCode(dx);
        result = 31 * result + Double.hashCode(dy);
        result = 31 * result + Double.hashCode(dz);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StraightLineEntity{point(")
                .append(px).append(", ").append(py).append(", ").append(pz).append("), direction[")
                .append(dx).append(", ").append(dy).append(", ").append(dz).append("]}");
        return sb.toString();
    }
}
