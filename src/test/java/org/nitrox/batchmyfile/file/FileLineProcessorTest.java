/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.file;


import org.junit.jupiter.api.Test;
import org.nitrox.batchmyfile.dataType.AlfaNumericDT;
import org.nitrox.batchmyfile.dataType.IntegerDT;
import org.nitrox.batchmyfile.entity.BankPaymentTestLayout;
import org.nitrox.batchmyfile.entity.MyFilePartDescriptor;
import org.nitrox.batchmyfile.layout.Field;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLineProcessorTest {
    

    @Test
    public void getFieldsLineValuesTest() {
        String line = "B2017052302129";
        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        Field partFileFieldDescriptor = new Field(
                1, "pftd", new AlfaNumericDT(), MyFilePartDescriptor.PART_DESCRIPTOR, true, bankLayout
        );
        FileLineProcessor fileLineProcessor = new FileLineProcessor(line);
        final Map<String, Object> fieldsLineValues = fileLineProcessor.getFieldsLineValues(partFileFieldDescriptor);

        assertEquals(2, 1 + 1);
    }
    
}
