class DiagramAnalizer {
    
    private final String KEY = "###";
    
    private String diagramText;

    public DiagramAnalizer(String diagramText) {
        this.diagramText = diagramText;
    }
    
    public String[] getClasses() {
        String[] result;
        diagramText = setModificators(diagramText);
        diagramText = markClasses(diagramText);
        result = diagramText.split(KEY);
        result = clearCode(result);
        for (int i = 0; i < result.length; i++) {
            result[i] = markMethods(result[i]);
        }
        return result;
    }

    
    private String markClasses(String diagramText) {
        String text = diagramText.replaceAll("interface ", KEY + "interface ");
        text = text.replaceAll("class ", KEY + "class ");
        text = text.replaceAll("}", "}" + KEY);
        return text;
    }

    
    private String markMethods(String classText) {
        String result = "";
        String[] lines = classText.split("\n");
        String type = "";
        for (int i = 0; i < lines.length; i++) {
             String s = lines[i];
             if (s.contains(":")) {
                String[] ss = s.split(":");
                type = ss[ss.length - 1];
                s = s.replaceAll("private ", "private" + type + " ");
                s = s.replaceAll("public ", "public" + type + " ");
                s = s.replaceAll(":.*", ";\n");
                if (!classText.contains("interface ")) {
                    s = s.replaceAll("\\).*", ") {\n        //TODO:\n    }\n");
                } 
             } else if (s.contains(")")) {
                s = s.replaceAll("\\)", ") {\n        //TODO:\n    }\n");
             } else if (!s.contains("{") && !s.contains("}")) {
                s = s + ";";
             }
             result += s + "\n";
        } 
        return result;
    }

    
    private String setModificators(String diagramText) {
        String result = diagramText.replaceAll("\\-", "private");
        result = result.replaceAll("\\+", "public");
        return result;
    }

    
    private String[] clearCode(String[] classesText) {
        String clearText = "";
        for (String s: classesText) {
            if (s.contains("class ") || s.contains("interface ")) {
                clearText += s + KEY;
            }
        }
        return clearText.split(KEY);
    }

}
