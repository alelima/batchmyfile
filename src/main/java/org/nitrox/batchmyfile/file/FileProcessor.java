/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.Layout;

public class FileProcessor {

    public Map<FilePartType, List<StructuredProcessedLine>> process(File file, Layout layout) {

        Map<FilePartType, List<StructuredProcessedLine>> fileValues = null;
        try {
            fileValues = importaInformacoesArquivo(file, layout);
        } catch (IOException e) {
            // TODO: Gerar Exceção aqui depois
            e.printStackTrace();
        }
        return fileValues;
    }

    private Map<FilePartType, List<StructuredProcessedLine>> importaInformacoesArquivo(File arquivo, Layout layout) throws IOException {
        Field partFileDescriptorField = layout.getPartFileDescriptorField();
        List<StructuredProcessedLine> structuredLines = new ArrayList<>();
        transformFileLinesInStructuredLines(arquivo, layout, partFileDescriptorField, structuredLines);

        return structuredLines.stream()
                .collect(Collectors.groupingBy(StructuredProcessedLine::getFilePartTypeOfLine));


    }

    private void transformFileLinesInStructuredLines(File arquivo, Layout layout, Field partFileDescriptorField, List<StructuredProcessedLine> structuredLines) throws IOException {
        Files.lines(arquivo.toPath()).forEach(
                linha -> {
                    FileLineProcessor lineProcessor = new FileLineProcessor(linha);
                    FilePartType filePartType = lineProcessor.getLinePartFileType(partFileDescriptorField);
                    Map<String, Object> lineValues = lineProcessor.getFieldsLineValues(filePartType, layout);
                    StructuredProcessedLine spl = new StructuredProcessedLine(filePartType, lineValues);
                    structuredLines.add(spl);
                }
        );
    }
}
