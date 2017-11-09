package org.nitrox.batchmyfile.builder;

import org.nitrox.batchmyfile.dataType.DataTypeConverter;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.Field;

public class BuildSteps implements LayoutFieldBuilder.NameStep, LayoutFieldBuilder.SizeStep,
        LayoutFieldBuilder.DataTypeStep, LayoutFieldBuilder.FilePartTypeStep,
        LayoutFieldBuilder.ObligatoryStep, LayoutFieldBuilder.BuildStep {

    private int size;

    private String name;

    private DataTypeConverter dataTypeConverter;

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
    public LayoutFieldBuilder.FilePartTypeStep dataType(DataTypeConverter dataTypeConverter) {
        this.dataTypeConverter = dataTypeConverter;
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
        Field layoutField = new Field(size, name, dataTypeConverter, filePartType, obligatory);
        return layoutField;
    }
}
