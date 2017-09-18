/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.Date;
import java.util.Map;
import org.nitrox.batchmyfile.dataType.DataType;
import org.nitrox.batchmyfile.util.DateUtil;
import org.nitrox.batchmyfile.util.ReflectionUtil;
import org.nitrox.batchmyfile.util.StringUtil;

/**
 *
 * @author 03883182443
 */
public class FieldConversor {

    public static Object stringToObject(String fieldValue, Field field) {
        DataType dataType = field.getDataType();
        return dataType.toObject(fieldValue);
    }

    @SuppressWarnings("rawtypes")
    public static String objectToString(Object fieldValue, Field field) {
        DataType dataType = field.getDataType();
        String valor = dataType.toString(fieldValue); 
        return StringUtil.insertZeros(valor, field.size());
    }

    /**
     * Esse método pega os valores dos campos que vem no map na forma
     * "nomeAtributo => Valor" e preenche esses valores nos atributos que
     * possuem o mesmo nome dentro do objeto;
     *
     * @param valores - Map que possui os valores a serem preenchidos no Objeto
     * @param objeto - Objeto a ser preenchido
     */
    public static void preencheObjeto(Object objeto, Map<String, Object> valores) {
        for (java.lang.reflect.Field campo : ReflectionUtil.obterCampos(objeto.getClass())) {
            boolean acessivel = campo.isAccessible();
            try {
                campo.setAccessible(true);
                Object valor = valores.get(campo.getName());
                if (valor == null) {
                    continue;
                }
                campo.set(objeto, valor);
            } catch (RuntimeException | IllegalAccessException excecao) {
                // ignora
            } finally {
                campo.setAccessible(acessivel);
            }
        }
    }

    /**
     * Esse método pega os valores dos atributos de um Objeto e preenche um Map
     * na forma "nomeAtributo => Valor"
     *
     * @param valores - Map a ser preenchido
     * @param objeto - Objeto que irá preencher suas informações no Map
     */
    public static void preencheValores(Map<String, Object> valores, Object objeto) {
        for (java.lang.reflect.Field campo : ReflectionUtil.obterCampos(objeto.getClass())) {
            boolean acessivel = campo.isAccessible();
            try {
                campo.setAccessible(true);
                Object valor = campo.get(objeto);
                valores.put(campo.getName(), valor);
            } catch (RuntimeException | IllegalAccessException excecao) {
                // ignora
            } finally {
                campo.setAccessible(acessivel);
            }
        }
    }
}
