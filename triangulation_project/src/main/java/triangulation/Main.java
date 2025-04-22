package triangulation;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String readFilePath = "triangulation_project\\src\\inputData2\\";
        String writeFilePath = "triangulation_project\\src\\outputImages2\\";
        PolygonGenerator generator = new PolygonGenerator();
        generator.generatePolygons(readFilePath, 20);
        ModelWriter pngWriter = new PNGWriter();
        TextReader textReader = new TextReader(readFilePath);
        

        List<List<Point>> polygons = textReader.getPolygons();
     
        
        for (int i = 0; i < polygons.size(); i++) {

            String currentWriteFilePath = writeFilePath + (i + 1) + "_Polygon.png";
            pngWriter.write(currentWriteFilePath, polygons.get(i));
        }
        
        for (Object elem : polygons) {
            System.out.println(elem);
        }
        System.out.println(polygons.size() + " polygons written to PNG files.");
    }
}
