package org.nitrox.batchmyfile.exception;

public class ProcessPositionalFileException extends RuntimeException {

    private String fieldName;

    private String lineNumber;

    private String position;

    private String line;

    public ProcessPositionalFileException(String message, Throwable cause, String fieldName,
                                          String lineNumber, String position, String line) {
        super(message, cause);
        this.fieldName = fieldName;
        this.lineNumber = lineNumber;
        this.position = position;
        this.line = line;
    }

    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nError in field ");
        sb.append(fieldName);
        sb.append(" in position ");
        sb.append(position);
        sb.append(" from line number ");
        sb.append(lineNumber);
        sb.append("\nin line: ");
        sb.append(line);
        sb.append("\ntotal line size: ");
        sb.append(line.length());
        sb.append("\n");
        sb.append(super.getMessage());
        return sb.toString();
    }

}
