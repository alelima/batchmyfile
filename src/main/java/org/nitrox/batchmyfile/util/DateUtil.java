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

    public static final String PADRAO_DATA = "dd/MM/yyyy";
    public static final String PADRAO_DATA_ARQUIVO = "yyyyMMdd";
    public static final String PADRAO_DATA_NOME_ARQUIVO = "yyMMdd";
    public static final String PADRAO_DATA_HORA_ARQUIVO = "ddMMyyyy HHmm";
    public static final String PADRAO_DATAHORA_COMPLETA = "dd/MM/yyyy HH:mm:ss";
    public static final String PADRAO_HORA_ARQUIVO = "HHmm";
    public static final String PADRAO_HORA_NOME_ARQUIVO = "HHmmss";
    public static final String DATA_ARQUIVO_DEFAULT = "01011970";
    private static final String REGEXP_HORA_MINUTO = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public static Date atribuirHoraNaData(Date date, Date hour) {
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(date);

        Calendar hourCal = Calendar.getInstance();
        hourCal.setTime(hour);

        dateTime.set(Calendar.HOUR_OF_DAY, hourCal.get(Calendar.HOUR_OF_DAY));
        dateTime.set(Calendar.MINUTE, hourCal.get(Calendar.MINUTE));
        dateTime.set(Calendar.SECOND, hourCal.get(Calendar.SECOND));

        return dateTime.getTime();
    }

    public static boolean isDataValida(String dataString) {
        if (PADRAO_DATA.length() != dataString.length()) {
            return false;
        }
        return toDate(dataString, PADRAO_DATA) != null;
    }

    public static boolean isHoraValida(String hora) {
        return Pattern.compile(REGEXP_HORA_MINUTO).matcher(hora).matches();
    }

    public static Date toDate(LocalDate localDate, String horaMinuto) {
        if ((localDate == null) || (horaMinuto == null)) {
            throw new IllegalArgumentException();
        }

        int hora = Integer.parseInt(horaMinuto.substring(0, 2));
        int minuto = Integer.parseInt(horaMinuto.substring(3, 5));
        return Date.from(localDate.atTime(hora, minuto).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(String dateString, String formato) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            sdf.setLenient(false);
            return sdf.parse(dateString);
        } catch (ParseException excecao) {
            throw new BatchMyFileException("Not possible parse the date", excecao);
        }
    }

    public static Date horaEmData(String hora, String data, String formato) {
        return toDate(data + " " + hora, formato);
    }

    public static String toString(Date data, String formato) {
        if (data == null) {
            return null;
        }
        return new SimpleDateFormat(formato).format(data);
    }

    public static String toStringArquivo(Date data) {
        return toString(data, PADRAO_DATA_ARQUIVO);
    }

    public static String toStringHoraArquivo(Date data) {
        return toString(data, PADRAO_HORA_ARQUIVO);
    }
}
