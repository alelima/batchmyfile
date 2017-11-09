/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.nitrox.batchmyfile.dataType.AlfaNumericDTC;
import org.nitrox.batchmyfile.entity.BankPaymentTestLayout;
import org.nitrox.batchmyfile.entity.MyFilePartDescriptor;
import org.nitrox.batchmyfile.exception.ProcessPositionalFileException;
import org.nitrox.batchmyfile.layout.Field;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("LineProcessor")
@DisplayName("Testing the file line processor")
public class FileLineProcessorTest {
    

    @Test
    @Tag("Fields")
    public void processTest() {
        String line = "B20170523143502129";
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.MAY, 23, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date expectedDate = cal.getTime();
        Double expectedValue = Double.valueOf("2.12");
        LocalTime expectedHour = LocalTime.of(14, 35);

        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        Field partFileFieldDescriptor = new Field(
                1, "pftd", new AlfaNumericDTC(), MyFilePartDescriptor.PART_DESCRIPTOR, true
        );
        FileLineProcessor fileLineProcessor = new FileLineProcessor(line, 1L);

        final StructuredProcessedLine structuredLine = fileLineProcessor.process(partFileFieldDescriptor, bankLayout);

        assertEquals(structuredLine.getLine().get("datePayment"), expectedDate);
        assertEquals(structuredLine.getLine().get("hourPayment"), expectedHour);
        assertEquals(structuredLine.getLine().get("valuePayment"), expectedValue);
    }

    @Test
    @Tag("Fields")
    public void processErrorDataTest() {
        String line = "B200523143502129";
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.MAY, 23, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date expectedDate = cal.getTime();
        Double expectedValue = Double.valueOf("2.12");
        LocalTime expectedHour = LocalTime.of(14, 35);

        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        Field partFileFieldDescriptor = new Field(
                1, "pftd", new AlfaNumericDTC(), MyFilePartDescriptor.PART_DESCRIPTOR, true
        );
        FileLineProcessor fileLineProcessor = new FileLineProcessor(line, 1L);

        assertThrows(ProcessPositionalFileException.class,
                () -> fileLineProcessor.process(partFileFieldDescriptor, bankLayout)) ;

    }

    @Test
    @Tag("Fields")
    public void processErrorTest() {
        String line = "B2017052314350    ";
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.MAY, 23, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date expectedDate = cal.getTime();
        LocalTime expectedHour = LocalTime.of(14, 35);

        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        Field partFileFieldDescriptor = new Field(
                1, "pftd", new AlfaNumericDTC(), MyFilePartDescriptor.PART_DESCRIPTOR, true
        );
        FileLineProcessor fileLineProcessor = new FileLineProcessor(line, 1L);

//        assertThrows(ProcessPositionalFileException.class,
//                () -> fileLineProcessor.process(partFileFieldDescriptor, bankLayout)) ;

        final StructuredProcessedLine structuredLine = fileLineProcessor.process(partFileFieldDescriptor, bankLayout);

        assertEquals(structuredLine.getLine().get("datePayment"), expectedDate);
        assertEquals(structuredLine.getLine().get("hourPayment"), expectedHour);
        assertEquals(structuredLine.getLine().get("valuePayment"), null);

    }
}
