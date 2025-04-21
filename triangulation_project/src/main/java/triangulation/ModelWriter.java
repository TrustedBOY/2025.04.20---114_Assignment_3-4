package triangulation;

import java.util.List;

public abstract class ModelWriter {

    public abstract void write(String path, List<Point>[] polygons);
    public abstract void write(String path, List<Point> vertices);

    public int getHighestX(List<Point>[] polygons) {
        int highestX = 0;
        for (List<Point> polygon : polygons) {
            highestX = Math.max(highestX, getHighestX(polygon));

        }
        return highestX;
    }

    public int getHighestX(List<Point> vertices){
        int highestX = 0;
        for (Point point : vertices) {
            highestX = Math.max(highestX, (int) point.x);
        }
        return highestX;
    }
}
