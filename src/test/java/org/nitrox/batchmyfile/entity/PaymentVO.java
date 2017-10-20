package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.conversor.ConvertedFileValue;
import org.nitrox.batchmyfile.layout.LayoutField;

import java.time.LocalTime;
import java.util.Date;

public class PaymentVO implements ConvertedFileValue {

    @LayoutField(name = "datePayment")
    private Date dateOfPayment;

    @LayoutField(name = "hourPayment")
    private LocalTime hourOfPayment;

    @LayoutField(name = "valuePayment")
    private Double valueOfPayment;

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public LocalTime getHourOfPayment() {
        return hourOfPayment;
    }

    public void setHourOfPayment(LocalTime hourOfPayment) {
        this.hourOfPayment = hourOfPayment;
    }

    public Double getValueOfPayment() {
        return valueOfPayment;
    }

    public void setValueOfPayment(Double valueOfPayment) {
        this.valueOfPayment = valueOfPayment;
    }
}
