package triangulation;

import java.util.List;

public class Triangle extends Geometry {

    private static final double thresholdRatio = 0.1;

    public Triangle(List<Point> vertices) {
        super(vertices);
        if (vertices.size() != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 vertices.");
        }
        if (!isValidTriangel(vertices)) {
            throw new IllegalArgumentException("The provided vertices do not form a valid triangle.");
        }
        if (isThin()) {
            throw new IllegalArgumentException("The triangle is too thin.");
        }
    }


    protected void write(){
        
    }

    public boolean isValidTriangel(List<Point> vertices) {
        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        return (a + b > c) && (a + c > b) && (b + c > a);

    }

    public boolean isThin(){
        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        double minSide = Math.min(a, Math.min(b, c));
        double maxSide = Math.max(a, Math.max(b, c));

        return (minSide / maxSide) < thresholdRatio; // Example threshold for thinness
    }

}
