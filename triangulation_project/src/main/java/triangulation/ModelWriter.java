package triangulation;

import java.util.List;

public abstract class ModelWriter {

    public ModelWriter() {
    }
    


    public abstract void write(String path, List<Point>[] polygons);
    public abstract void write(String path, List<Point> vertices);

    public double getMaxY(List<Point>[] polygons) {
        double maxY = polygons[0].get(0).y;
        for (List<Point> polygon : polygons) {
            maxY = Math.max(maxY, getMaxY(polygon));

        }
        return maxY;
    }

    public double getMaxY(List<Point> vertices){
        double maxY = vertices.get(0).y;
        for (Point point : vertices) {
            maxY = Math.max(maxY,point.y);
        }
        return maxY;
    }

    public double getMinY(List<Point>[] polygons) {
        double minY = polygons[0].get(0).y;
        for (List<Point> polygon : polygons) {
            minY = Math.min(minY, getMinY(polygon));

        }
        return minY;
    }

    public double getMinY(List<Point> vertices){
        double minY = vertices.get(0).y;
        for (Point point : vertices) {
            minY = Math.min(minY, point.y);
        }
        return minY;
    }

    public double getMaxX(List<Point>[] polygons) {
        double maxX = polygons[0].get(0).x;
        for (List<Point> polygon : polygons) {
            maxX = Math.max(maxX, getMaxX(polygon));

        }
        return maxX;
    }

    public double getMaxX(List<Point> vertices){
        double maxX = vertices.get(0).x;
        for (Point point : vertices) {
            maxX = Math.max(maxX, point.x);
        }
        return maxX;
    }
    
    public double getMinX(List<Point>[] polygons) {
        double minX = polygons[0].get(0).x;
        for (List<Point> polygon : polygons) {
            minX = Math.min(minX, getMinX(polygon));

        }
        return minX;
    }

    public double getMinX(List<Point> vertices){
        double minX = vertices.get(0).x;
        for (Point point : vertices) {
            minX = Math.min(minX, point.x);
        }
        return minX;
    }

}
