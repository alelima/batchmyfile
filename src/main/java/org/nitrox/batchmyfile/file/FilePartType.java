/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


public interface FilePartType {

    public String getValue();

    @SuppressWarnings("unchecked")
//    public default Class<? extends Enum> getEnumClass() {
//        String teste = "";
//        return (Class<Enum<?>>) this.getClass();
//    }

    public static <T extends FilePartType> FilePartType getFilePartTypeByValue(String typeValue, Class<T> enumType) {
        for (FilePartType e : enumType.getEnumConstants()) {
            if(e.getValue().equals(typeValue)) {
                return e;
            }
        }
        return null;
    }

    //TODO: Future Test here, can't not return null
    public static <T extends FilePartType> FilePartType valueOf(String typeValue, Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
	    return (FilePartType) Enum.valueOf((Class<? extends Enum>)enums[0].getClass(), typeValue);
    }

}
