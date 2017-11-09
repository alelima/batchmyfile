package org.nitrox.batchmyfile;

import org.nitrox.batchmyfile.converter.ConvertedFileValue;
import org.nitrox.batchmyfile.converter.FileValueConverter;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.file.FileProcessor;
import org.nitrox.batchmyfile.file.StructuredProcessedLine;
import org.nitrox.batchmyfile.layout.Layout;

import java.io.File;
import java.util.List;
import java.util.Map;

public class BatchMyFile {

    private Layout layout;

    private File file;

    private ConvertedFileValue cfv;

    public BatchMyFile ofFile(File file) {
        this.file = file;
        return this;
    }

    public BatchMyFile withLayout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public BatchMyFile forThis(ConvertedFileValue convertedValue) {
        this.cfv = convertedValue;
        return this;
    }

    public ConvertedFileValue process() {
        FileProcessor fileProcessor = new FileProcessor();
        Map<FilePartType, List<StructuredProcessedLine>> structuredFile =
                fileProcessor.process(this.file, this.layout);

        FileValueConverter fileConversor = new FileValueConverter();
        return fileConversor.convert(this.cfv, this.layout, structuredFile);
    }
}
