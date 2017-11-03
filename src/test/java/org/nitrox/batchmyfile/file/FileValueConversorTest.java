package org.nitrox.batchmyfile.file;

import javafx.util.converter.LocalTimeStringConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.nitrox.batchmyfile.conversor.ConvertedFileValue;
import org.nitrox.batchmyfile.conversor.FileValueConversor;
import org.nitrox.batchmyfile.entity.BankDayPaymentsVO;
import org.nitrox.batchmyfile.entity.BankPaymentTestLayout;
import org.nitrox.batchmyfile.entity.MyFilePartDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("LineProcessor")
@DisplayName("Testing the file line processor")
public class FileValueConversorTest {

    @Test
    public void testGenericListType() throws NoSuchFieldException {
        Field paymentsField = BankDayPaymentsVO.class.getDeclaredField("payments");
        ParameterizedType listType = (ParameterizedType) paymentsField.getGenericType();
        Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
        System.out.println(listClass); // class java.lang.String.0

    }

    @Test
    public void convertTest() {

        BankDayPaymentsVO bankVO = new BankDayPaymentsVO();
        BankPaymentTestLayout bankLayout = new BankPaymentTestLayout();
        FileValueConversor conversor = new FileValueConversor();

        Map<String, Object> headerLine = new HashMap<>();
        headerLine.put("dateCreation", new Date());
        headerLine.put("fileSequential", new Integer(5));
        StructuredProcessedLine headerStructuredLine = new StructuredProcessedLine(MyFilePartDescriptor.HEADER, headerLine);

        Map<String, Object> detailLine1 = new HashMap<>();
        detailLine1.put("datePayment", new Date());
        detailLine1.put("hourPayment", LocalTime.of(12, 30,01,0));
        detailLine1.put("valuePayment", new Double("124.54"));
        StructuredProcessedLine detailStructuredLine1 = new StructuredProcessedLine(MyFilePartDescriptor.DETAIL, detailLine1);

        Map<String, Object> detailLine2 = new HashMap<>();
        detailLine2.put("datePayment", new Date());
        detailLine2.put("hourPayment", LocalTime.of(01, 15,01,0));
        detailLine2.put("valuePayment", new Double("240.78"));
        StructuredProcessedLine detailStructuredLine2 = new StructuredProcessedLine(MyFilePartDescriptor.DETAIL, detailLine2);

        Map<String, Object> detailLine3 = new HashMap<>();
        detailLine3.put("datePayment", new Date());
        detailLine3.put("hourPayment", LocalTime.of(8, 45,01,0));
        detailLine3.put("valuePayment", new Double("45.67"));
        StructuredProcessedLine detailStructuredLine3 = new StructuredProcessedLine(MyFilePartDescriptor.DETAIL, detailLine3);

        Map<String, Object> traillerLine = new HashMap<>();
        traillerLine.put("numberLines", new Integer(3));
        StructuredProcessedLine traillerStructuredLine = new StructuredProcessedLine(MyFilePartDescriptor.TRAILLER, traillerLine);

        List<StructuredProcessedLine> headers = new ArrayList<>();
        headers.add(headerStructuredLine);

        List<StructuredProcessedLine> details = new ArrayList<>();
        details.add(detailStructuredLine1);
        details.add(detailStructuredLine2);
        details.add(detailStructuredLine3);

        List<StructuredProcessedLine> traillers = new ArrayList<>();
        traillers.add(traillerStructuredLine);

        Map<FilePartType, List<StructuredProcessedLine>> structuredFile = new HashMap<>();
        structuredFile.put(MyFilePartDescriptor.HEADER, headers);
        structuredFile.put(MyFilePartDescriptor.DETAIL, details);
        structuredFile.put(MyFilePartDescriptor.TRAILLER, traillers);

        conversor.convert(bankVO, bankLayout, structuredFile);

        assertEquals(bankVO.getNumberOfPayments(), new Integer(3));
        assertEquals(bankVO.getPayments().size(), 3);
    }

}
