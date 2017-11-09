/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import org.nitrox.batchmyfile.dataType.DataTypeConverter;
import org.nitrox.batchmyfile.file.FilePartType;

/**
 * This class representing a field of a positional file.
 * @author Alessandro Lima
 */
public class Field {

    /**
     * The value of all positions occupied by this field in positional file.
     */
    private int size;

    /**
     * The name of field in your layout specification
     */
    private String name;

    /**
     * The specific converter for the data type wanted
     */
    private DataTypeConverter dataTypeConverter;

    /**
     * The part of file which this field belongs
     */
    private FilePartType filePartTipe;

    /**
     * Representing if value representation of this field is in file is obligatory or not.
     */
    private boolean obligatory;

    /**
     * Representing if this field is a part file descriptor.
     */
    private boolean partFileDescriptor = false;

    /**
     * Constructor of LayoutField class.
     * I recommend using instead of this constructor the LayoutFieldBuilder.
     *
     * @param size
     * @param name
     * @param dataTypeConverter
     * @param filePartTipe
     * @param obligatory
     */
    public Field(int size, String name, DataTypeConverter dataTypeConverter, FilePartType filePartTipe, boolean obligatory) {
        this.size = size;
        this.name = name;
        this.dataTypeConverter = dataTypeConverter;
        this.filePartTipe = filePartTipe;
        this.obligatory = obligatory;
    }
    
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public DataTypeConverter getDataTypeConverter() {
        return dataTypeConverter;
    }

    public FilePartType getFilePartTipe() {
        return filePartTipe;
    }

    public boolean isObligatory() {
        return obligatory;
    }


    public boolean isPartFileDescriptor() {
        return partFileDescriptor;
    }

    protected void setPartFileDescriptor(boolean partFileDescriptor) {
        this.partFileDescriptor = partFileDescriptor;
    }
}
