/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.FieldConversor;
import org.nitrox.batchmyfile.layout.Layout;

/**
 *
 * @author Alessandro Lima
 */
public class FileLineProcessor {

    //private static final Logger LOG = LoggerFactory.getLogger(ProcessadorLinha.class);

    public static final int START_OF_LINE = 0;

    String line;
    private int cursor = 0;

    public FileLineProcessor(String line) {
        this.line = line;
    }

    private Object getFieldValue(Field field) {
        int range = field.getSize() + cursor;
        String fieldValue = "";
        Object value = null;
        try {
            fieldValue = this.line.substring(cursor, range);
            value = FieldConversor.stringToObject(fieldValue, field);
        } catch (Exception e) {
            e.printStackTrace();
            String textoErro = "\n Erro no campo " + field + " na posição " + cursor + " da linha "
                    + this.line + "\n Tamanho linha: " + this.line.length();
            //LOG.error(textoErro);
            throw new RuntimeException(textoErro);
        }
        cursor = range;
        return value;
    }

    public Map<String, Object> getLineFieldsValues(List<Field> fields) {
        this.cursor = START_OF_LINE;
        LinkedHashMap<String, Object> fieldValues = new LinkedHashMap<>();
        fields.forEach(field -> fieldValues.put(field.getName(), this.getFieldValue(field)));
        return fieldValues;
    }

    public Map<String, Object> getFieldsLineValues(Field partFileDescriptorField) {
        String partFileDescriptorValue = this.getFieldValue(partFileDescriptorField).toString();
        FilePartType filePartType = FilePartType.getFilePartTypeByValue(partFileDescriptorValue);
        Layout layout = partFileDescriptorField.getLayout();
        fillLineIfSmallerThanTotalSize(partFileDescriptorField, filePartType);
        return getLineValues(layout, filePartType);
    }

    private Map<String, Object> getLineValues(Layout layout, FilePartType filePartType) {
        List<Field> fields = layout.getFieldsByFilePartType(filePartType);
        return this.getLineFieldsValues(fields);
    }

    private void fillLineIfSmallerThanTotalSize (Field partFileDescriptorField, FilePartType filePartType) {
        int totalLineChar = partFileDescriptorField.getLayout().getLineSizeByFilePartType(filePartType);
        if (line.length() < totalLineChar) {
            line = String.format("%-" + totalLineChar + "s", line);
        }
    }

    public String getLineByFieldValues(Map<String, Object> valoresCampo, List<Field> camposLinha) {
        Field campo = camposLinha.get(0);
        if (campo == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Field campoArquivo : camposLinha) {
            Object valorObjeto = valoresCampo.get(campoArquivo.getName());
            String valor = FieldConversor.objectToString(valorObjeto, campo);
            sb.append(valor);
        }

        return sb.toString();
    }

}
