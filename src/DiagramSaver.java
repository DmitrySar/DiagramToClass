import java.io.*;
import java.nio.file.*;

class DiagramSaver {
    
    private  String[] classesText;   

    private  Path path;

    public DiagramSaver(String[] classesText, Path path) {
    	this.classesText = classesText;
    	this.path = path;
    }
    
    public void toFiles() throws IOException {
		for (String s: classesText) {
			String[] ss = s.trim().split(" ");
            String fileName = path.toString() + "\\" + ss[1] + ".java";
            System.out.println(fileName);
            Files.write(Paths.get(fileName), s.getBytes());      
		}
    }

}
