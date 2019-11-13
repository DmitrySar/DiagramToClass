import java.nio.file.*;
import java.io.*;

class DiagramController {
    
    private static String inputFileName = "classDiagram.txt";

    private static String outputPath = "src";
    
    private DiagramReader reader;
    
    private DiagramAnalizer analizer;
    
    private DiagramSaver saver;
    
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            inputFileName = args[0];
            outputPath = args[1];
        }
    	DiagramController controller = new DiagramController();
    	controller.start();
    }

    private void start() throws IOException {
    	reader = new DiagramReader(Paths.get(inputFileName));
    	analizer = new DiagramAnalizer(reader.getDiagramText());
    	saver = new DiagramSaver(analizer.getClasses(), Paths.get(outputPath));
    	saver.toFiles();
    }

}
