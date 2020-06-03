package com.taxInvioce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    @Rule
    public MockitoRule mockito= MockitoJUnit.rule();

    @Mock
    RideRepository rideRepository;

    @Mock
    ISubscription subscription;

    @InjectMocks
    InvoiceService invoiceService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenDistanceAndTimeForNormalRide_shouldReturnTotalFare() {
        invoiceService = new InvoiceService(RideType.NORMAL_RIDE);
        double distance =2.0;
        int time=5;
        when(subscription.addRides(2.0,5)).thenReturn(25.0);
        double fare= invoiceService.addRides(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTimeForNormalRide_shouldReturnMinimunFare() {
        InvoiceService invoiceService = new InvoiceService(RideType.NORMAL_RIDE);
        double fare= invoiceService.addRides(.1,1);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRidesForNormalRide_shouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService(RideType.NORMAL_RIDE);
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1,1)};
        when(rideRepository.getRides(ArgumentMatchers.any())).thenReturn(rides);
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRideForNormalRide_shouldReturnInvoiceSummary() {
       try{
            String userId="milan";
            InvoiceService invoiceService = new InvoiceService(RideType.NORMAL_RIDE);
            Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)};
            invoiceService.addRides(userId,rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
            Assert.assertEquals(expectedInvoiceSummary,summary);
       }catch(InvoiceException e){
           e.printStackTrace();
           Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
       }
    }

    @Test
    public void givenDistanceAndTimeForPremiumRide_shouldReturnTotalFare() {
        InvoiceService invoiceService = new InvoiceService(RideType.PREMIUM_RIDE);
        double distance =2.0;
        int time=5;
        double fare= invoiceService.addRides(distance,time);
        Assert.assertEquals(40,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTimeForPremiumRide_shouldReturnMinimunFare() {
        InvoiceService invoiceService = new InvoiceService(RideType.PREMIUM_RIDE);
        double distance=.1;
        int time=1;
        double fare= invoiceService.addRides(distance,time);
        Assert.assertEquals(20,fare,0.0);
    }

    @Test
    public void givenMultipleRidesForPremiumRide_shouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService(RideType.PREMIUM_RIDE);
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)};
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRideForPremiumRide_shouldReturnInvoiceSummary() {
        try{
            String userId="milan";
            InvoiceService invoiceService = new InvoiceService(RideType.PREMIUM_RIDE);
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1,1)};
            invoiceService.addRides(userId,rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
            Assert.assertEquals(expectedInvoiceSummary,summary);
        }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
        }
    }

}
