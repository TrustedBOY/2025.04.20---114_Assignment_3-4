package triangulation;

import java.util.List;

public class Polygon extends Geometry {

    public Polygon(List<Point> vertices) {
        super(vertices);
    }

    @Override
    public double area(){
            // This method calculates the area of the polygon using the shoelace formula.

            double area = 0.0;
            for (int i = 0; i < vertices.size(); i++) {
    
                try {
                    Point p1 = vertices.get(i);
                    Point p2 = vertices.get((i + 1) % vertices.size());
                    area += (p1.x * p2.y) - (p2.x * p1.y);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds in: " + getClass() + ".area() | " + e.getMessage());
                }
    
            }
            return Math.abs(area) / 2.0;
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon: ");
        for (Point vertex : vertices) {
            sb.append(vertex.toString()).append(" ");
        }
        return sb.toString().trim();
    }

    

}
