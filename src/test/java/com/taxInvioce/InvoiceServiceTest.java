package com.taxInvioce;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {
    
    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance =2.0;
        int time=5;
        double fare= invoiceService.addRides(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinimunFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance=.1;
        int time=1;
        double fare= invoiceService.addRides(distance,time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1,1)};
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);

    }

    @Test
    public void givenUserIdAndRide_shouldReturnInvoiceSummary() {
        String userId="milan";
        InvoiceService invoiceService = new InvoiceService();
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)};
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}
