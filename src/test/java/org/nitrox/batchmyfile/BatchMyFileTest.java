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
                ofFile(new File("")).
                process();

        assertEquals(new Integer(4), bankVO.getNumberOfPayments());
    }
}
