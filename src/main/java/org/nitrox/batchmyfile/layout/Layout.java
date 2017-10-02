/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.nitrox.batchmyfile.file.FilePartType;

/**
 *
 * @author Alessandro Lima
 */
public interface Layout {

    public List<Field> getFields();
    
    public default Field getPartFileDescriptorField() {
        java.lang.reflect.Field[] fields = this.getClass().getFields();
        for (java.lang.reflect.Field field : fields) {
            if(field.isAnnotationPresent(PartFileDescriptorField.class)) {
                try {
                    return (Field)field.get(this);
                } catch (IllegalArgumentException|IllegalAccessException ex) {
                    Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        return null;
    }

    public default List<Field> getFieldsByFilePartType(FilePartType filePartType) {
        List<Field> fieldsByFilePart = getFields().stream().filter(field
                -> field.getPartFile().equals(filePartType)).collect(Collectors.toList());
        return fieldsByFilePart;
    }

    public default Optional<Field> getFieldByName(String name) {
        return getFields().stream().filter(field -> field.getName().equals(name)).findFirst();
    }    

    public default int getLineSizeByFilePartType(FilePartType filePartType) {
        List<Field> fields = getFieldsByFilePartType(filePartType);
        return fields.stream().mapToInt(field -> field.size()).sum();
    }
}
