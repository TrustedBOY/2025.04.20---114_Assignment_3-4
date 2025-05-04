package triangulation;

public final class Point {
    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point other = (Point) o;
        return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString(){
        return "P: (" + x + ", " + y + ")";
    }

}
