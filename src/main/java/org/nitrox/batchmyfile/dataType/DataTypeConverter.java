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
public interface DataTypeConverter {
    public Object toObject(String value);
    
    public String toString(Object value);
}
