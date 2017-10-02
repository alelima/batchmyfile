/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import org.nitrox.batchmyfile.dataType.DataType;
import org.nitrox.batchmyfile.file.FilePartType;

/**
 *
 * @author Alessandro Lima
 */
public class Field {

    private int size;

    private String name;

    private DataType dataType;

    private FilePartType filePartTipe;
    
    private boolean obligatory;
    
    private Layout layout;

    public Field(int size, String name, DataType dataType, FilePartType filePartTipe, boolean obligatory, Layout layout) {
        this.size = size;
        this.name = name;
        this.dataType = dataType;
        this.filePartTipe = filePartTipe;
        this.obligatory = obligatory;
        this.layout = layout;
    }
    
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public FilePartType getFilePartTipe() {
        return filePartTipe;
    }

    public boolean isObligatory() {
        return obligatory;
    }

    public Layout getLayout() {
        return layout;
    }
}
