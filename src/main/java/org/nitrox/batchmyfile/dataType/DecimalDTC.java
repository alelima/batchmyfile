/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.dataType;

/**
 *
 * @author Alessandro Lima
 */
public class DecimalDTC implements DataTypeConverter {
    
    private int numberOfDecimalPlaces = 2;
    
    private boolean useSeparatorInPrint = false;

    public DecimalDTC(int numberOfDecimalPlaces) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
    }
    
    public DecimalDTC(boolean useSeparatorInPrint) {
        this.useSeparatorInPrint = useSeparatorInPrint;
    }
    
    public DecimalDTC(int numberOfDecimalPlaces, boolean useSeparatorInPrint) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
        this.useSeparatorInPrint = useSeparatorInPrint;
    }
    
    public DecimalDTC() {
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
