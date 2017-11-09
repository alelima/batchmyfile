/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.nitrox.batchmyfile.exception.ProcessPositionalFileException;
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.FieldConversor;
import org.nitrox.batchmyfile.layout.Layout;

public class FileLineProcessor {

    public static final int START_OF_LINE = 0;

    String line;
    long lineNumber;
    String originalLine;

    private int cursor = 0;

    public FileLineProcessor(String line, long lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.originalLine = line;
    }

    public StructuredProcessedLine process(Field genericPartFileDescriptorField, Layout layout) {
        FilePartType filePartType = this.getLinePartFileType(genericPartFileDescriptorField);
        Map<String, Object> lineValues = this.getFieldsLineValues(filePartType, layout);
        return new StructuredProcessedLine(filePartType, lineValues);
    }

    private Map<String, Object> getFieldsLineValues(FilePartType filePartType, Layout layout) {
        fillLineIfSmallerThanTotalSize(layout, filePartType);
        List<Field> fields = layout.getFieldsByFilePartType(filePartType);
        return this.getLineFieldsValues(fields);
    }

    private FilePartType getLinePartFileType(Field genericPartFileDescriptorField) {
        String partFileDescriptorValue = this.getFieldValue(genericPartFileDescriptorField).toString();
        Class partFileDescClass = genericPartFileDescriptorField.getFilePartTipe().getClass();
        this.line = line.substring(1, line.length());
        return FilePartType.getFilePartTypeByValue(partFileDescriptorValue, partFileDescClass);
    }

    private void fillLineIfSmallerThanTotalSize (Layout layout, FilePartType filePartType) {
        int totalLineChar = layout.getLineSizeByFilePartType(filePartType);
        if (line.length() < totalLineChar) {
            line = String.format("%-" + totalLineChar + "s", line);
        }
    }

    private Object getFieldValue(Field field) {
        int range = field.getSize() + cursor;
        String fieldValue = "";
        Object value = null;
        try {
            fieldValue = this.line.substring(cursor, range);
            value = FieldConversor.stringToObject(fieldValue, field);
        } catch (Exception e) {
            if(field.isObligatory()) {
                String message = "Error in get the file value";
                throw new ProcessPositionalFileException(message, e, field.getName(),
                        Long.toString(lineNumber), Integer.toString(cursor + 1), originalLine);
            }
        }
        cursor = range;
        return value;
    }

    private Map<String, Object> getLineFieldsValues(List<Field> fields) {
        this.cursor = START_OF_LINE;
        LinkedHashMap<String, Object> fieldValues = new LinkedHashMap<>();
        fields.forEach(field -> fieldValues.put(field.getName(), this.getFieldValue(field)));
        return fieldValues;
    }
}
