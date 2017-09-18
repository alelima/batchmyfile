/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.dataType;

/**
 *
 * @author 03883182443
 */
public class DecimalDT implements DataType {
    
    private int numberOfDecimalPlaces = 2;
    
    private boolean useSeparatorInPrint = false;

    public DecimalDT(int numberOfDecimalPlaces) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
    }
    
    public DecimalDT(boolean useSeparatorInPrint) {
        this.useSeparatorInPrint = useSeparatorInPrint;
    }
    
    public DecimalDT(int numberOfDecimalPlaces, boolean useSeparatorInPrint) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
        this.useSeparatorInPrint = useSeparatorInPrint;
    }
    
    public DecimalDT() {
        //
    }
    
    @Override
    public Object toObject(String value) {
        String absoluteValue = value.substring(0, value.length() - numberOfDecimalPlaces);
        String decimalValue = value.substring(value.length() - numberOfDecimalPlaces, value.length());
        return new Double(absoluteValue + "." + decimalValue);
    }

    @Override
    public String toString(Object value) {
        String stringValue = value.toString();
        if(!useSeparatorInPrint) {
            stringValue = stringValue.replace(".", ""); 
        }
        return stringValue;
    }

}
