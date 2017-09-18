/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;

/**
 *
 * @author 03883182443
 */
public enum FilePartType {
    HEADER, 
    DETAIL,
    TRAILLER, 
    PART_DESCRIPTOR;
    
    public static FilePartType getFilePartTypebyValue(
			String valorTipo) {
		FilePartType[] tipos = values();
		for (int i = 0; i < tipos.length; i++) {
//			if (tipos[i].valorEmpresa.equals(valorTipo)
//					|| tipos[i].valorBanco.equals(valorTipo)
//					|| tipos[i].valorCorreios.equals(valorTipo)) {
//				return tipos[i];
//			}
		}
		return null;
	}
    
    public static boolean isValueValid(String valorTipo) {
		if(getFilePartTypebyValue(valorTipo) == null) {
			return false;
		}
		return true;
	}
}
