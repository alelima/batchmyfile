/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.nitrox.batchmyfile.file.FilePartType;

/**
 *
 * @author 03883182443
 */
public interface Layout {

    public List<Field> getFields();

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
