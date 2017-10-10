/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


public interface FilePartType {

    public String getValue();

	public static <T extends FilePartType> FilePartType getFilePartTypeByValue(String typeValue, Class<T> enumType) {
        for (FilePartType e : enumType.getEnumConstants()) {
            if(e.getValue().equals(typeValue)) {
                return e;
            }
        }
        return null;
    }

}
