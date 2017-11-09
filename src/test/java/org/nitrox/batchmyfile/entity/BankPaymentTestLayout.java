/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.builder.LayoutFieldBuilder;
import org.nitrox.batchmyfile.dataType.*;
import org.nitrox.batchmyfile.file.DefaultFilePartType;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.Layout;
import org.nitrox.batchmyfile.layout.PartFileDescriptorField;
import org.nitrox.batchmyfile.util.DateUtil;

import java.util.Arrays;
import java.util.List;

public class BankPaymentTestLayout implements Layout {

    @PartFileDescriptorField
    private final Field partFileDescription = LayoutFieldBuilder.newBuilder()
            .name("partFileDescriptor")
            .size(1)
            .dataType(new AlfaNumericDT())
            .filePartType(MyFilePartDescriptor.PART_DESCRIPTOR)
            .obligatory(true)
            .build();

    //HEADER
    private final Field dateCreation = LayoutFieldBuilder.newBuilder()
            .name("dateCreation")
            .size(8)
            .dataType(new DateDT("yyyyMMdd"))
            .filePartType(MyFilePartDescriptor.HEADER)
            .obligatory(true)
            .build();

    private final Field fileSequential = new Field(5, "fileSequential", new IntegerDT(), MyFilePartDescriptor.HEADER, true);

    //DETAIL
    private final Field datePayment = new Field(8, "datePayment", new DateDT("yyyyMMdd"), MyFilePartDescriptor.DETAIL, true);

    private final Field hourPayment =  new Field(4, "hourPayment", new HourDT(), MyFilePartDescriptor.DETAIL, true);

    private final Field valuePayment = new Field(4, "valuePayment", new DecimalDT(), MyFilePartDescriptor.DETAIL, false);

    //TRAILLER
    private final Field numberLines = new Field(4, "numberLines", new IntegerDT(), MyFilePartDescriptor.TRAILLER, true);


    @Override
    public List<Field> getFields() {
        return Arrays.asList(partFileDescription, dateCreation, fileSequential,
                datePayment, hourPayment, valuePayment, numberLines);
    }

}
