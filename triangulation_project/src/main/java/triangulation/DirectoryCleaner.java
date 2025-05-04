package triangulation;

import java.io.File;

public class DirectoryCleaner {

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
}
