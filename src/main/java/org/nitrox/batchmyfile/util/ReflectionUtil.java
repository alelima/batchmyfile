/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 03883182443
 */
public class ReflectionUtil {
    
    private static void carregarCampos(Class<?> clazz, List<Field> campos) {
        if (clazz.equals(Object.class)) {
            return;
        }
        for (Field campo : clazz.getDeclaredFields()) {
            campos.add(campo);
        }
        carregarCampos(clazz.getSuperclass(), campos);
    }

    public static Field[] obterCampos(Class<?> clazz) {
        List<Field> campos = new ArrayList<Field>();
        carregarCampos(clazz, campos);
        return campos.toArray(new Field[campos.size()]);
    }

    public static Object obterValor(Field campo, Object objeto) {
        boolean acessivel = campo.isAccessible();
        try {
            campo.setAccessible(true);
            return campo.get(objeto);
        } catch (RuntimeException | IllegalAccessException excecao) {
            throw new IllegalStateException("Erro ao acessar " + campo.getName() + " em " + objeto.getClass(), excecao);
        } finally {
            campo.setAccessible(acessivel);
        }
    }
    
    public static void preencheAtributo(String nomeCampo, Object objeto, Object valor) {		
        for (Field campo : obterCampos(objeto.getClass())) {
            boolean acessivel = campo.isAccessible();
            try {
            	campo.setAccessible(true);
            	if(campo.getName().equals(nomeCampo)) {
            		campo.set(objeto, valor);
            	} 
            } catch (RuntimeException | IllegalAccessException e) {
                // ignora
            } finally {
                campo.setAccessible(acessivel);
            }
        }
    }
}
