package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.conversor.ConvertedFileValue;
import org.nitrox.batchmyfile.layout.LayoutField;
import org.nitrox.batchmyfile.layout.LayoutPart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankDayPaymentsVO implements ConvertedFileValue {

    @LayoutField(name="dateCreation", partFile = "HEADER")
    private Date sentDate;

    @LayoutField(name="fileSequential", partFile = "HEADER")
    private Integer numberOfFile;

    @LayoutPart(filePartName = "DETAIL")
    private List<PaymentVO> payments = new ArrayList<>();

    @LayoutField(name="numberLines", partFile = "TRAILLER")
    private Integer numberOfPayments;

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Integer getNumberOfFile() {
        return numberOfFile;
    }

    public void setNumberOfFile(Integer numberOfFile) {
        this.numberOfFile = numberOfFile;
    }

    public List<PaymentVO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentVO> payments) {
        this.payments = payments;
    }

    public Integer getNumberOfPayments() {
        return numberOfPayments;
    }

    public void setNumberOfPayments(Integer numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }
}
