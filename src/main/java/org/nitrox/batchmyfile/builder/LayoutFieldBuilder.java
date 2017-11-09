package org.nitrox.batchmyfile.builder;

import org.nitrox.batchmyfile.dataType.DataType;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.Field;

public class LayoutFieldBuilder {

    private LayoutFieldBuilder() {

    }

    public static NameStep newBuilder() {
        return new BuildSteps();
    }

    public static interface NameStep {
        SizeStep name(String name);
    }

    public static interface SizeStep {
        DataTypeStep size(int size);
    }

    public static interface DataTypeStep {
        public FilePartTypeStep dataType(DataType dataType);
    }

    public static interface FilePartTypeStep {
        public ObligatoryStep filePartType(FilePartType filePartType);
    }

    public static interface ObligatoryStep {
        public BuildStep obligatory(boolean obligatory);
    }

    public static interface BuildStep {
        public Field build();
    }
}
