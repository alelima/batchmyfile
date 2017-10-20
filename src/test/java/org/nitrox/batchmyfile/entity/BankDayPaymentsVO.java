package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.conversor.ConvertedFileValue;
import org.nitrox.batchmyfile.layout.LayoutPart;

import java.util.ArrayList;
import java.util.List;

public class BankDayPaymentsVO implements ConvertedFileValue {

    @LayoutPart(filePartClass = MyFilePartDescriptor.class, filePartName = "DETAIL")
    private List<PaymentVO> payments = new ArrayList<>();

}
