package org.nitrox.batchmyfile.conversor;

import org.nitrox.batchmyfile.exception.ConversionFromLayoutException;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.file.StructuredProcessedLine;
import org.nitrox.batchmyfile.layout.Layout;
import org.nitrox.batchmyfile.layout.LayoutField;
import org.nitrox.batchmyfile.layout.LayoutPart;
import org.nitrox.batchmyfile.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileValueConversor {

    private static Logger LOGGER = Logger.getLogger(FileValueConversor.class.getName());


    public ConvertedFileValue convert(ConvertedFileValue convertedFileValue, Layout layout, Map<FilePartType, List<StructuredProcessedLine>> structuredFile) {
        List<Field> fields = ReflectionUtil.getListFields(convertedFileValue.getClass());
        Class partFileDescriptorClass = layout.getPartFileDescriptorField().getFilePartTipe().getClass();
        List<StructuredProcessedLine> lines = null;

        Map<Boolean, List<Field>> fieldsAnnoted = fields.stream().collect(Collectors.groupingBy(f -> (Boolean) f.isAnnotationPresent(LayoutField.class)));
        List<Field> fieldAnnotedLayoutField = fieldsAnnoted.get(Boolean.TRUE);
        fieldAnnotedLayoutField = fieldAnnotedLayoutField != null ? fieldAnnotedLayoutField : new ArrayList<>();

        fieldsAnnoted = fields.stream().collect(Collectors.groupingBy(f -> (Boolean) f.isAnnotationPresent(LayoutPart.class)));
        List<Field> fieldsAnnotedLayoutPart = fieldsAnnoted.get(Boolean.TRUE);
        fieldsAnnotedLayoutPart = fieldsAnnotedLayoutPart != null ? fieldsAnnotedLayoutPart : new ArrayList<>();

        Map<FilePartType, List<Field>> fieldsByFilePart = fillFieldsByFilePart(partFileDescriptorClass, fieldAnnotedLayoutField);

        for (Map.Entry<FilePartType, List<Field>> entry : fieldsByFilePart.entrySet()) {
            FilePartType filePart = entry.getKey();
            convert(convertedFileValue, entry.getValue(), structuredFile.get(filePart));
        }


        for (Field field : fieldsAnnotedLayoutPart) {
            if (field.getType().equals(List.class)) {
                field.setAccessible(true);
                List<ConvertedFileValue> listValues;
                try {
                    listValues = (List<ConvertedFileValue>) field.get(convertedFileValue);
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class<?> listTypeClass = (Class<?>) listType.getActualTypeArguments()[0];

                    LayoutPart layoutPart = field.getAnnotation(LayoutPart.class);
                    String filePartName = layoutPart.filePartName();
                    FilePartType partTypeOfField = FilePartType.valueOf(filePartName, partFileDescriptorClass);

                    List<StructuredProcessedLine> spLines = structuredFile.get(partTypeOfField);
                    spLines = spLines != null ? spLines : new ArrayList<>();

                    int amountLines = spLines.size();

                    for (int i = 0; i < amountLines; i++) {
                        ConvertedFileValue cfv = (ConvertedFileValue) listTypeClass.newInstance();
                        cfv = this.convert(cfv, layout, structuredFile);
                        listValues.add(cfv);
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
            }
        }
        return convertedFileValue;
    }

    private void convert(ConvertedFileValue fileValue, List<Field> fields,
                         List<StructuredProcessedLine> structuredLines) {
        if (structuredLines.isEmpty()) {
            return;
        }

        StructuredProcessedLine line = structuredLines.get(0);
        for (Field field : fields) {
            LayoutField layoutField = field.getAnnotation(LayoutField.class);
            String fieldName = layoutField.name();

            if (!line.getLine().containsKey(fieldName)) {
                String keyNotFoundMessage = "Do not have a " + fieldName
                        + " as field name in your layout," +
                        " verify the field name in your LayoutField annotation";
                LOGGER.log(Level.WARNING, keyNotFoundMessage);
            }

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

            fieldsByFilePart.computeIfAbsent(partTypeOfField, k -> new ArrayList<>());

            fieldsByFilePart.get(partTypeOfField).add(field);
        }
        return fieldsByFilePart;
    }



    private void fillField(ConvertedFileValue fileValue, Field field, Object value) {
        boolean acessible = field.isAccessible();
        try {
            field.setAccessible(!acessible);
            field.set(fileValue, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            handleIAException(fileValue, field, value, iae);
        } finally {
            field.setAccessible(acessible);
        }
    }

    private void handleIAException(ConvertedFileValue fileValue, Field field, Object value, IllegalArgumentException iae) {
        String actualClass = field.getType().toString();
        String expectedClass = value.getClass().toString();
        String message = "The Layout Definition type is " + expectedClass
                + " but your type is " + actualClass;
        throw new ConversionFromLayoutException(message, iae);
    }
}
