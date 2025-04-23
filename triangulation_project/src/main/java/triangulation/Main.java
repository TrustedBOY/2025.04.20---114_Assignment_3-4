package triangulation;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String readFilePath = "triangulation_project\\src\\inputData2\\";
        String writeFilePath = "triangulation_project\\src\\outputImages\\";
        
        ModelWriter pngWriter = new PNGWriter();
        TextReader textReader = new TextReader(readFilePath);

        List<List<Point>> polygons = textReader.getPolygons();
        System.out.println(polygons.size() + " polygons read from text files.");

        Polygon polygon = new Polygon(polygons.get(0));
        Triangulation triangulation = new Triangulation(polygon);
        triangulation.triangulate();

        System.out.println(triangulation.getTriangles().toString());

        List<Point>[] vertices = triangulation.getVertices();
        pngWriter.write(writeFilePath, vertices);

        for (int i = 0; i < vertices.length ; i++) {
            System.out.println("hi");
            String currentWriteFilePath = writeFilePath + (i + 1) + "_Polygon.png";
            pngWriter.write(currentWriteFilePath, vertices[i]);
        }

    
        // for (int i = 0; i < polygons.size(); i++) {

        //     String currentWriteFilePath = writeFilePath + (i + 1) + "_Polygon.png";
        //     pngWriter.write(currentWriteFilePath, polygons.get(i));
        // }

        System.out.println(polygons.size() + " polygons written to PNG files.");
    }
}
