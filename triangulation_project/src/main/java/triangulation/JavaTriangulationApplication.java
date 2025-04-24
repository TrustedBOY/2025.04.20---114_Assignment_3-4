package triangulation;

import java.io.File;
import java.util.List;

public class JavaTriangulationApplication {

    private String readFilePath;
    private String writeFilePath;

    public JavaTriangulationApplication(String readFilePath, String writeFilePath) {
        this.readFilePath = readFilePath + File.separator;
        this.writeFilePath = writeFilePath + File.separator;
    }

    public void run() {
        ModelWriter pngWriter = new PNGWriter();
        TextReader textReader = new TextReader(readFilePath);

        List<List<Point>> polygons = textReader.getPolygons();
        System.out.println(polygons.size() + " polygons read from text files.");

        for (int i = 0; i < polygons.size(); i++) {

            Polygon polygon = new Polygon(polygons.get(i));

            String currentWriteFilePath = writeFilePath + (i + 1) + "_Polygon.png";
            pngWriter.write(currentWriteFilePath, polygons.get(i));

            currentWriteFilePath = writeFilePath + (i + 1) + "_Triangle.png";
            Triangulation triangulation = new Triangulation(polygon);
            triangulation.triangulate();
            List<Point>[] vertices = triangulation.getVertices();
            pngWriter.write(currentWriteFilePath, vertices);
        }

    }

}
