/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


public enum FilePartType {
    HEADER(0),
    DETAIL(1),
    TRAILLER(2),
    PART_DESCRIPTOR(-1);

    private int value;

	private FilePartType(int value) {
		this.value = value;
	}

	public static FilePartType getFilePartTypeByValue(String typeValue) {
		int valueToCompare = Integer.parseInt(typeValue);
		FilePartType[] types = values();
		for (int i = 0; i < types.length; i++) {
			if (types[i].value == valueToCompare) {
				return types[i];
			}
		}
		return null;
	}
    
    public static boolean isValueValid(String typeValue) {
		if(getFilePartTypeByValue(typeValue) == null) {
			return false;
		}
		return true;
	}
}
