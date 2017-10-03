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
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.Layout;

public class FileProcessor {

    public List<Map<String, Object>> process(File file, Layout layout) {

        List<Map<String, Object>> valoresArquivo = new ArrayList<>();
        try {
            importaInformacoesArquivo(file, valoresArquivo, layout);
        } catch (IOException e) {
            // TODO: Gerar Exceção aqui depois
            e.printStackTrace();
        }
        return valoresArquivo;
    }

    private void importaInformacoesArquivo(File arquivo, List<Map<String, Object>> valoresArquivo, Layout layout) throws IOException {

        //LineIterator linhas;
        Field partFileDescriptorField = layout.getPartFileDescriptorField();
        Files.lines(arquivo.toPath()).forEach(
                linha -> {
                    FileLineProcessor lineProcessor = new FileLineProcessor(linha);
                    valoresArquivo.add(lineProcessor.getFieldsLineValues(partFileDescriptorField));
                }
        );

//        linhas = FileUtils.lineIterator(arquivo, "UTF-8");
//
//        while (linhas.hasNext()) {
//            String linha = linhas.next();
//
//            if (linha.length() == 0 || !FilePartType.isValueValid(linha.substring(0, 1))) {
//                continue;
//            }
//
//            FileLineProcessor lineProcessor = new FileLineProcessor(linha);
//            valoresArquivo.add(lineProcessor.getFieldsLineValues(partFileDescriptorField));
//        }
    }
}
