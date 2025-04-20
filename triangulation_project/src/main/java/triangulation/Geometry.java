package triangulation;

import java.util.ArrayList;
import java.util.List;

public abstract class Geometry {
    protected final List<Point> vertices;

    public Geometry(List<Point> vertices) {
        if (vertices == null || vertices.size() < 3) {
            throw new IllegalArgumentException("A shape must have at least 3 vertices.");
        }
        this.vertices = new ArrayList<>(vertices);
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public double distance(Point p1 , Point p2){
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y,2));
    }

    public abstract double area();
}
