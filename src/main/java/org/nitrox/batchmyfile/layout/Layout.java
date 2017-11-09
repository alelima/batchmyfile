/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.annotation.PartFileDescriptorField;
import org.nitrox.batchmyfile.util.ReflectionUtil;

/**
 * Layout is the class representation of a real positional file layout.
 * All fields in a positional file layout should be represented by a field in this
 * class.
 */
@FunctionalInterface
public interface Layout {

    /**
     * This method should return all fields of the Layout.
     * @return List of all fields in Layout
     */
    public List<Field> getFields();

    /**
     * Return the Part File Descriptor field of Layout, this Part File returned is
     * the field with PartFileDescriptorField annotation.
     * @return field representing the value of part of file.
     */
    public default Field getPartFileDescriptorField() {
        java.lang.reflect.Field[] fields = ReflectionUtil.getFields(this.getClass());
        for (java.lang.reflect.Field field : fields) {
            if(field.isAnnotationPresent(PartFileDescriptorField.class)) {
                try {
                    field.setAccessible(true);
                    ((Field) field.get(this)).setPartFileDescriptor(true);
                    return (Field)field.get(this);
                } catch (IllegalArgumentException|IllegalAccessException ex) {
                    Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    field.setAccessible(false);
                }

            }
        }
        return null; //TODO: Remove this null
    }

    /**
     * Return all fields in the same part file (ex: all files of header)
     * @param filePartType the part file for desired fields
     * @return list of fields which are part of the same part file.
     */
    public default List<Field> getFieldsByFilePartType(FilePartType filePartType) {
        List<Field> fieldsByFilePart = getFields().stream().filter(field
                -> field.getFilePartTipe().equals(filePartType) && !field.isPartFileDescriptor())
                .collect(Collectors.toList());
        return fieldsByFilePart;
    }

    /**
     * Return the summed size of all fields which are part of the same part file.     *
     * @param filePartType the part file for desired fields
     * @return the size of one line of part file passed
     */
    public default int getLineSizeByFilePartType(FilePartType filePartType) {
        List<Field> fields = getFieldsByFilePartType(filePartType);
        return fields.stream().mapToInt(field -> field.getSize()).sum();
    }
}
