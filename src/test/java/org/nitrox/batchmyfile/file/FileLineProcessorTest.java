/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.nitrox.batchmyfile.dataType.AlfaNumericDT;
import org.nitrox.batchmyfile.entity.BankPaymentTestLayout;
import org.nitrox.batchmyfile.entity.MyFilePartDescriptor;
import org.nitrox.batchmyfile.layout.Field;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("LineProcessor")
@DisplayName("Testing the file line processor")
public class FileLineProcessorTest {
    

    @Test
    @Tag("Fields")
    public void getFieldsLineValuesTest() {
        String line = "B20170523143502129";
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.MAY, 23, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date expectedDate = cal.getTime();
        Double expectedValue = Double.valueOf("2.12");
        LocalTime expectedHour = LocalTime.of(14, 35);

        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        Field partFileFieldDescriptor = new Field(
                1, "pftd", new AlfaNumericDT(), MyFilePartDescriptor.PART_DESCRIPTOR, true, bankLayout
        );
        FileLineProcessor fileLineProcessor = new FileLineProcessor(line);
        //final Map<String, Object> fieldsLineValues = fileLineProcessor.getFieldsLineValues(partFileFieldDescriptor);

//        assertEquals(fieldsLineValues.get("datePayment"), expectedDate);
//        assertEquals(fieldsLineValues.get("hourPayment"), expectedHour);
//        assertEquals(fieldsLineValues.get("valuePayment"), expectedValue);
    }
}
