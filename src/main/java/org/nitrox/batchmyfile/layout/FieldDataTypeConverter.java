/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import org.nitrox.batchmyfile.dataType.DataTypeConverter;
import org.nitrox.batchmyfile.util.StringUtil;

/**
 *
 * @author Alessandro Lima
 */
public class FieldDataTypeConverter {

    /**
     * This method get the string value that was taken from file and
     * convert in the data type specified by DataTypeConverter
     * used in LayoutField creation.
     *
     * @param fieldValue the value taken from file
     * @param field the Field representation in Layout
     * @return an object converted by data type converter
     */
    public static Object stringToObject(String fieldValue, Field field) {
        DataTypeConverter dataTypeConverter = field.getDataTypeConverter();
        return dataTypeConverter.toObject(fieldValue);
    }


    public static String objectToString(Object fieldValue, Field field) {
        DataTypeConverter dataTypeConverter = field.getDataTypeConverter();
        String valor = dataTypeConverter.toString(fieldValue);
        return StringUtil.insertZeros(valor, field.getSize());
    }
}
