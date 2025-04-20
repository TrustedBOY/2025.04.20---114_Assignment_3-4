package triangulation;

public abstract class Geometry {
    protected Point[] vertices;

    public Geometry(Point[] vertices) {
        this.vertices = vertices;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public abstract double area();
}
