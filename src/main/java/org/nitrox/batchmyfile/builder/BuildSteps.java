package org.nitrox.batchmyfile.builder;

import org.nitrox.batchmyfile.dataType.DataType;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.Layout;

public class BuildSteps implements LayoutFieldBuilder.NameStep, LayoutFieldBuilder.SizeStep,
        LayoutFieldBuilder.DataTypeStep, LayoutFieldBuilder.FilePartTypeStep,
        LayoutFieldBuilder.ObligatoryStep, LayoutFieldBuilder.BuildStep {

    private int size;

    private String name;

    private DataType dataType;

    private FilePartType filePartType;

    private boolean obligatory;

    @Override
    public LayoutFieldBuilder.SizeStep name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public LayoutFieldBuilder.DataTypeStep size(int size) {
        this.size = size;
        return this;
    }

    @Override
    public LayoutFieldBuilder.FilePartTypeStep dataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    @Override
    public LayoutFieldBuilder.ObligatoryStep filePartType(FilePartType filePartType) {
        this.filePartType = filePartType;
        return this;
    }

    @Override
    public LayoutFieldBuilder.BuildStep obligatory(boolean obligatory) {
        this.obligatory = obligatory;
        return this;
    }

    @Override
    public Field build() {
        Field layoutField = new Field(size, name, dataType, filePartType, obligatory);
        return layoutField;
    }
}
