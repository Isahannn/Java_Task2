package org.example.entity;

public class Point {
    private final Double x;
    private final Double y;
    private final Double z;

    public Point(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Double getX() { return x; }
    public Double getY() { return y; }
    public Double getZ() { return z; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Point{x=")
                .append(x)
                .append(", y=")
                .append(y)
                .append(", z=")
                .append(z )
                .append("}");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        if (x != null ? !x.equals(point.x) : point.x != null) return false;
        if (y != null ? !y.equals(point.y) : point.y != null) return false;
        if (z != null ? !z.equals(point.z) : point.z != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }
}
