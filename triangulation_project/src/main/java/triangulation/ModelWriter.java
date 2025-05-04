package triangulation;

import java.io.File;
import java.util.List;

public abstract class ModelWriter {

    public ModelWriter() {
    }
    


    public abstract void writePolygons(String path, List<List<Point>> polygons);
    public abstract void writePolygon(String path, List<Point> vertices);

    public static void clearDirectory(String path) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("Directory cleared: " + path);
        } else {
            System.out.println("Directory does not exist or is not a directory: " + path);
        }
    }
    
    public double getMax(List<?> polygonsOrVertices, boolean isX) {
        if (polygonsOrVertices == null || polygonsOrVertices.isEmpty()) {
            return 0;
        }

        double max = Double.NEGATIVE_INFINITY;

        if (polygonsOrVertices.get(0) instanceof List) {
            for (Object polygon : polygonsOrVertices) {
                max = Math.max(max, getMax((List<?>) polygon, isX));
            }
        } else if (polygonsOrVertices.get(0) instanceof Point) {
            for (Object point : polygonsOrVertices) {
                Point p = (Point) point;
                max = Math.max(max, isX ? p.x : p.y);
            }
        }

        return max;
    }

    public double getMin(List<?> polygonsOrVertices, boolean isX) {
        if (polygonsOrVertices == null || polygonsOrVertices.isEmpty()) {
            return 0;
        }

        double min = Double.POSITIVE_INFINITY;

        if (polygonsOrVertices.get(0) instanceof List) {
            for (Object polygon : polygonsOrVertices) {
                min = Math.min(min, getMin((List<?>) polygon, isX));
            }
        } else if (polygonsOrVertices.get(0) instanceof Point) {
            for (Object point : polygonsOrVertices) {
                Point p = (Point) point;
                min = Math.min(min, isX ? p.x : p.y);
            }
        }

        return min;
    }

}
