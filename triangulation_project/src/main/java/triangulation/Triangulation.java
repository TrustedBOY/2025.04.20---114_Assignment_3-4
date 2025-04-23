package triangulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangulation {

    private final Polygon polygon;
    private List<Triangle> triangles = new ArrayList<>();

    public Triangulation(Polygon polygon) {
        this.polygon = polygon;
    }

    public void triangulate() {

        System.out.println(polygon.getVertices().size());
        triangulate(polygon, 0);
        
    }
    public void triangulate(Polygon polygon , int index) {

        if (polygon.getVertices().size() < 3) return;
        
        System.out.println(index);
        for(int i = 0 ; i < polygon.getVertices().size() ; i++){
            Point a = polygon.getVertices().get(i);
            Point b = polygon.getVertices().get((i + 1) % polygon.getVertices().size());
            Point c = polygon.getVertices().get((i + 2) % polygon.getVertices().size());
            List<Point> vertices = new ArrayList<>(Arrays.asList(a, b, c));
            Triangle triangle = new Triangle(vertices);
            if (triangle.isValidTriangel(vertices)) {
                triangles.add(triangle);
                List<Point> newVertices = new ArrayList<>(polygon.getVertices());
                newVertices.remove(b);
                
                if (newVertices.size() < 3) return;

                Polygon newPolygon = new Polygon(newVertices);
                triangulate(newPolygon, index + 1);
            }
        }

    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    public List<Point>[] getVertices(){
        List<Point>[] vertices = new List[triangles.size()];
        for (int i = 0; i < triangles.size(); i++) {
            vertices[i] = triangles.get(i).getVertices();
        }
        return vertices;
    }
}
