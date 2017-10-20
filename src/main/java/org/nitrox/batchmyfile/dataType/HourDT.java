/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.dataType;

import org.nitrox.batchmyfile.util.DateUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HourDT implements DataType{

    private String pattern = "HHmm";

    private DateTimeFormatter formatter;

    public HourDT() {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public HourDT(String pattern) {
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public Object toObject(String value) {
        LocalTime hour = LocalTime.parse(value, formatter);
        return hour;
    }

    @Override
    public String toString(Object value) {
        LocalTime hour = (LocalTime) value;
        return hour.format(formatter);
    }

    
}
