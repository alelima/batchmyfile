package org.nitrox.batchmyfile.conversor;

import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.file.StructuredProcessedLine;
import org.nitrox.batchmyfile.layout.Layout;
import org.nitrox.batchmyfile.layout.LayoutField;
import org.nitrox.batchmyfile.layout.LayoutPart;
import org.nitrox.batchmyfile.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileValueConversor {


    public ConvertedFileValue convert2(ConvertedFileValue fileValue, Layout layout, Map<FilePartType, List<StructuredProcessedLine>> structuredFile) {
        List<Field> fields = ReflectionUtil.getListFields(ConvertedFileValue.class);
        Class partFileDescriptorClass = layout.getPartFileDescriptorField().getFilePartTipe().getClass();
        List<StructuredProcessedLine> lines = null;

        Map<Boolean, List<Field>> fieldsAnnoted = fields.stream().collect(Collectors.groupingBy(f -> (Boolean) f.isAnnotationPresent(LayoutField.class)));
        List<Field> fieldAnnotedLayoutField = fieldsAnnoted.get(Boolean.TRUE);

        fieldsAnnoted = fields.stream().collect(Collectors.groupingBy(f -> (Boolean) f.isAnnotationPresent(LayoutPart.class)));
        List<Field> fieldAnnotedLayoutPart = fieldsAnnoted.get(Boolean.TRUE);


        Map<FilePartType, List<Field>> fieldsByFilePart = fillFieldsByFilePart(partFileDescriptorClass, fieldAnnotedLayoutField);

        for (Map.Entry<FilePartType, List<Field>> entry : fieldsByFilePart.entrySet()) {
            FilePartType filePart = entry.getKey();
            convert(fileValue, entry.getValue(), structuredFile.get(filePart));

        }


        //LayoutPart

        return fileValue;

    }

    private void convert(ConvertedFileValue fileValue, List<Field> fields,
                         List<StructuredProcessedLine> structuredLines) {
        if(structuredLines.isEmpty()) {
            return;
        }

        StructuredProcessedLine line = structuredLines.get(0);
        for (Field field : fields) {
            LayoutField layoutField = field.getAnnotation(LayoutField.class);
            String fieldName = layoutField.name();
            Object value = line.getLine().get(fieldName);
            fillField(fileValue, field, value);
        }

        structuredLines.remove(0);
    }

    private Map<FilePartType, List<Field>> fillFieldsByFilePart(Class partFileDescriptorClass, List<Field> fieldAnnotedLayoutField) {
        Map<FilePartType, List<Field>> fieldsByFilePart = new HashMap<>();

        for (Field field : fieldAnnotedLayoutField) {

            LayoutField layoutField = field.getAnnotation(LayoutField.class);
            String fieldName = layoutField.name();
            String partFileName = layoutField.partFile();


            FilePartType partTypeOfField = FilePartType.valueOf(partFileName, partFileDescriptorClass);

            if (fieldsByFilePart.get(partTypeOfField) == null) {
                fieldsByFilePart.put(partTypeOfField, new ArrayList<>());
            }

            fieldsByFilePart.get(partTypeOfField).add(field);

//            for (StructuredProcessedLine line : lines) {
//                Object value = line.getLine().get(fieldName);
//                fillField(fileValue, values, field);
//            }

        }
        return fieldsByFilePart;
    }


//    public ConvertedFileValue convert(ConvertedFileValue fileValue, List<Map<String, Object>> values) {
//        Class clazz = fileValue.getClass();
//
//        for (Field field  : ReflectionUtil.getFields(ConvertedFileValue.class)) {
//
//            if(field.isAnnotationPresent(LayoutField.class)) {
//                fillField(fileValue, values, field);
//                continue;
//            }
//
//            if(field.isAnnotationPresent(LayoutPart.class)) {
//                if(field.getType().equals(List.class)) {
//                    field.setAccessible(true);
//                    List<ConvertedFileValue> listValues = null;
//                    try {
//                        listValues = (List<ConvertedFileValue>) field.get(fileValue);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                    for (ConvertedFileValue cfv : listValues) {
//                        this.convert(cfv, values);
//                    }
//
//                }
//            }
//        }


    private void fillField(ConvertedFileValue fileValue, Field field, Object value) {
        boolean acessible = field.isAccessible();
        try {
            field.set(fileValue, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(acessible);
        }
    }
}
