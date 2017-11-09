/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    private static void getFields(Class<?> clazz, List<Field> campos) {
        if (clazz.equals(Object.class)) {
            return;
        }
        for (Field campo : clazz.getDeclaredFields()) {
            campos.add(campo);
        }
        getFields(clazz.getSuperclass(), campos);
    }

    public static Field[] getFields(Class<?> clazz) {
        List<Field> campos = new ArrayList<Field>();
        getFields(clazz, campos);
        return campos.toArray(new Field[campos.size()]);
    }

    public static List<Field> getListFields(Class<?> clazz) {
        List<Field> campos = new ArrayList<>();
        getFields(clazz, campos);
        return campos;
    }
}
