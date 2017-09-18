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
public interface DataType {
    public Object toObject(String value);
    
    public String toString(Object value);
}
