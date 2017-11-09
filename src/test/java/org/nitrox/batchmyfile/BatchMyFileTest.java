package org.nitrox.batchmyfile;

import org.junit.jupiter.api.Test;
import org.nitrox.batchmyfile.entity.BankDayPaymentsVO;
import org.nitrox.batchmyfile.entity.BankPaymentTestLayout;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BatchMyFileTest {

    @Test
    void testBatchMyFileProcess() {

        BankDayPaymentsVO bankVO = (BankDayPaymentsVO) new BatchMyFile().
                forThis(new BankDayPaymentsVO()).
                withLayout(new BankPaymentTestLayout()).
                withFile(new File("/home/03883182443/desenv/teste-bmf.txt")).
                process();

        assertEquals(new Integer(4), bankVO.getNumberOfPayments());
    }
}
