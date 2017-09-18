/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.List;
import org.nitrox.batchmyfile.file.FilePartType;

/**
 *
 * @author 03883182443
 */
public interface Layout {

    public Field[] getFields();

    List<Field> getFieldsByFilePartType(FilePartType filePartType);

    public Field getFieldByDescription();

    public default int getLineSizeByFilePartType(FilePartType filePartType) {
        List<Field> fields = getFieldsByFilePartType(filePartType);
        return fields.stream().mapToInt(field -> field.size()).sum();
    }
}
