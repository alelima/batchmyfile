package org.nitrox.batchmyfile.file;

import java.util.Map;

public class StructuredProcessedLine {

    private FilePartType filePartTypeOfLine;

    private Map<String, Object> line;

    public StructuredProcessedLine(FilePartType filePartTypeOfLine, Map<String, Object> line) {
        this.filePartTypeOfLine = filePartTypeOfLine;
        this.line = line;
    }

    public FilePartType getFilePartTypeOfLine() {
        return filePartTypeOfLine;
    }

    public Map<String, Object> getLine() {
        return line;
    }
}
