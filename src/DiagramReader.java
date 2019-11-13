import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

class DiagramReader {
    
    private Path fileName;
    private Charset charset = Charset.forName("UTF-8");

    public DiagramReader(Path fileName) {
    	this.fileName = fileName;
    }

    public DiagramReader(Path fileName, Charset charset) {
    	this.fileName = fileName;
    	this.charset = charset;
    }
    
    public String getDiagramText() throws IOException {
        String diagramText = arrayListToString(Files.readAllLines(fileName, charset));
        return diagramText;
    }

    private String arrayListToString(List<String> array) {
    	String result = "";
    	for (String s: array) {
    		result += s + "\n";
    	}
    	return result;
    }

}
