package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.conversor.FileValueConversor;
import org.nitrox.batchmyfile.file.FileProcessor;

import java.io.File;

public class BatchFileExample {

    public static void main(String[] args) {
        BankDayPaymentsVO bankVO = new BankDayPaymentsVO();
        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();

        FileProcessor fileProcessor = new FileProcessor();
        FileValueConversor conversor = new FileValueConversor();
        //File file = new File();

        //BankDayPaymentsVO bankVO2 = conversor.convert(bankVO, fileProcessor.process(file, bankLayout));


    }
}
