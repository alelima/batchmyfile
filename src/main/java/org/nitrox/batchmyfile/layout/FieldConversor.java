/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

import java.util.Date;
import java.util.Map;
import org.nitrox.batchmyfile.util.DateUtil;
import org.nitrox.batchmyfile.util.ReflectionUtil;
import org.nitrox.batchmyfile.util.StringUtil;

/**
 *
 * @author 03883182443
 */
public class FieldConversor {

    public static Object stringToObject(String fieldValue, Field field) {
        FieldType fieldType = field.getType();
        Object value;
        switch (fieldType) {
            case ALFANUMERIC:
                value = fieldValue;
                break;
            case INTERGER:
                value = new Integer(fieldValue);
                break;
            case DATE:
                value = DateUtil.toDate(fieldValue, DateUtil.PADRAO_DATA_ARQUIVO); //TODO: substituir por uma configuração global
                break;
            case HOUR:
                value = DateUtil.horaEmData(fieldValue, DateUtil.DATA_ARQUIVO_DEFAULT, DateUtil.PADRAO_DATA_HORA_ARQUIVO); //TODO: substituir por uma configuração global
                break;
            case DECIMAL:
                String absoluteValue = fieldValue.substring(0, fieldValue.length() - 2);
                String decimalValue = fieldValue.substring(fieldValue.length() - 2, fieldValue.length());
                value = new Double(absoluteValue + "." + decimalValue);
                break;
            default:
                value = null; //TODO: Avaliar usar Optional para não retornar null
                break;
        }
        return value;
    }

    @SuppressWarnings("rawtypes")
    public static String objectToString(Object fieldValue, Field field) {
        FieldType fieldType = field.getType();
        String valor = "";
        switch (fieldType) {
            case ALFANUMERIC:
                valor = (String) fieldValue;
                valor = valor == null ? "" : valor;
                valor = String.format("%-" + field.size() + "s", valor);
                return valor.substring(0, field.size());
            case INTERGER:
                valor = fieldValue.toString();
                break;
            case DATE:
                valor = DateUtil.toStringArquivo((Date) fieldValue);
                break;
            case HOUR:
                valor = DateUtil.toStringHoraArquivo((Date) fieldValue);
                break;
            case DECIMAL:
                valor = valor.replace(".", ""); //TODO: no futuro ter uma configuração para se decimais devem ir com ponto ou não
                break;
            case ENUMERATION:
                Enum enumCampo = (Enum) fieldValue;
                valor = String.valueOf(enumCampo.ordinal());
                break;
            default:
                valor = null;
                break;
        }
        return StringUtil.insertZeros(valor, field.size());
    }
    
    /**
	 * Esse método pega os valores dos campos que vem no map na forma "nomeAtributo => Valor" e preenche
	 * esses valores nos atributos que possuem o mesmo nome dentro do objeto;
	 * @param valores - Map que possui os valores a serem preenchidos no Objeto
	 * @param objeto - Objeto a ser preenchido
	 */
	public static void preencheObjeto(Object objeto, Map<String, Object> valores) {
        for (java.lang.reflect.Field campo : ReflectionUtil.obterCampos(objeto.getClass())) {
            boolean acessivel = campo.isAccessible();
            try {
                campo.setAccessible(true);
                Object valor = valores.get(campo.getName());
                if(valor == null) {
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
	 * Esse método pega os valores dos atributos de um Objeto e preenche um Map na forma "nomeAtributo => Valor"
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
