/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.dataType;

import java.util.Date;
import org.nitrox.batchmyfile.util.DateUtil;

/**
 *
 * @author 03883182443
 */
public class DateDT implements DataType {

    private String datePattern = "yyyyMMdd";
    
    public DateDT(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Object toObject(String value) {
        return DateUtil.toDate(value, datePattern); 
    }

    @Override
    public String toString(Object value) {
        return DateUtil.toString((Date) value, datePattern);
    }

}
