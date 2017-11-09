/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Alessandro Lima
 */
public class DateUtil {

    public static Date toDate(String dateString, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            return sdf.parse(dateString);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
    }

    public static String toString(Date data, String format) {
        if (data == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(data);
    }
}
