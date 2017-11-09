package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.converter.FileValueConverter;
import org.nitrox.batchmyfile.file.FileProcessor;

public class BatchFileExample {

    public static void main(String[] args) {
        BankDayPaymentsVO bankVO = new BankDayPaymentsVO();
        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        FileProcessor fileProcessor = new FileProcessor();
        FileValueConverter conversor = new FileValueConverter();
        //File file = new File();

        //BankDayPaymentsVO bankVO2 = conversor.convert(bankVO, fileProcessor.process(file, bankLayout));


    }
}
