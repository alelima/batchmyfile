/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.dataType.DateDT;
import org.nitrox.batchmyfile.dataType.DecimalDT;
import org.nitrox.batchmyfile.dataType.IntegerDT;
import org.nitrox.batchmyfile.file.FilePartType;
import org.nitrox.batchmyfile.layout.Field;
import org.nitrox.batchmyfile.layout.Layout;
import org.nitrox.batchmyfile.layout.PartFileDescriptorField;
import org.nitrox.batchmyfile.util.DateUtil;

import java.util.Arrays;
import java.util.List;

public class BankPaymentTestLayout implements Layout {

    @PartFileDescriptorField
    private final Field partFileDescription = new Field(1, "partFileDescriptor", new IntegerDT(), FilePartType.HEADER, true, this);

    //HEADER
    private final Field dateCreation = new Field(8, "dateCriation", new DateDT(DateUtil.PADRAO_DATA_ARQUIVO), FilePartType.HEADER, true, this);

    private final Field fileSequential = new Field(5, "fileSequential", new IntegerDT(), FilePartType.HEADER, true, this);

    //DETAIL
    private final Field datePayment = new Field(8, "datePayment", new DateDT(DateUtil.PADRAO_DATA_ARQUIVO), FilePartType.DETAIL, true, this);

    private final Field valuePayment = new Field(4, "valuePayment", new DecimalDT(), FilePartType.DETAIL, true, this);

    //TRAILLER
    private final Field numberLines = new Field(4, "number lines", new IntegerDT(), FilePartType.TRAILLER, true, this);


    @Override
    public List<Field> getFields() {
        return Arrays.asList(partFileDescription, dateCreation, fileSequential,
                datePayment, valuePayment, numberLines);
    }

}
