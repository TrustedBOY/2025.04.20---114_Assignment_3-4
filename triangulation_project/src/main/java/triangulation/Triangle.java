package triangulation;

import java.util.List;

public class Triangle extends Polygon {

    public static final double thinThresholdRatio = 0.1;

    public Triangle(List<Point> vertices) {
        super(vertices);
        if (vertices.size() != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 vertices.");
        }
        if (!isValidTriangle(vertices)) {
            throw new IllegalArgumentException("The provided points do not form a valid triangle.");
        }
        
        
    }

    public boolean isValidTriangle(List<Point> vertices) {
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

        return (minSide / maxSide) < Triangle.thinThresholdRatio; 
    }

    @Override
    public double area() {
        // Using Heron's formula to calculate the area of the triangle

        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    
    public String toString() {
        return "Triangle: " + vertices.get(0) + ", " + vertices.get(1) + ", " + vertices.get(2);
    }

    
}
