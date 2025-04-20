package triangulation;

public class Polygon extends Geometry{

    public Polygon(Point[] vertices) {
        super(vertices);
        if(!isClockwise(vertices)){
            // If the vertices are not in clockwise order, reverse the order.
            toClockwise();
        }
    }

    private boolean isClockwise(Point[] vertices){
        // This method checks if the polygon is in clockwise order.
        // The algorithm is based on the signed area of the polygon.

        double sum = 0.0;
        for (int i = 0; i < vertices.length; i++) {
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % vertices.length];
            sum += (p1.x * p2.y) - (p2.x * p1.y);
        }
        return sum < 0;
    }
    
    public double area() {
        // This method calculates the area of the polygon using the shoelace formula.
        
        double area = 0.0;
        for (int i = 0; i < vertices.length; i++) {
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % vertices.length];
            area += (p1.x * p2.y) - (p2.x * p1.y);
        }
        return Math.abs(area) / 2.0;
    }

    private void toClockwise(){
        // This method reverses the order of the vertices to make them clockwise.
        
        if(!isClockwise(vertices)){
            Point[] newVertices = new Point[vertices.length];
            for (int i = 0; i < vertices.length; i++) {
                newVertices[i] = vertices[vertices.length - (i+1)];
            }
            vertices = newVertices;
        }
    }

    

}
