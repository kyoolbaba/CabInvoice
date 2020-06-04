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

    @InjectMocks
    InvoiceService invoiceService=new InvoiceService();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenDistanceAndTimeForNormalRide_shouldReturnTotalFare() {
        double distance =2.0;
        int time=5;
        double fare= invoiceService.addRides(distance,time,RideType.NORMAL_RIDE);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTimeForNormalRide_shouldReturnMinimunFare() {
        double distance =.1;
        int time=1;
        double fare= invoiceService.addRides(distance,time,RideType.NORMAL_RIDE);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRidesForNormalRide_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5,RideType.NORMAL_RIDE),
                        new Ride(0.1,1,RideType.PREMIUM_RIDE)};
        when(rideRepository.getRides(ArgumentMatchers.any())).thenReturn(rides);
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRideForNormalRide_shouldReturnInvoiceSummary() {
       try{
            String userId="milan";
            Ride[] rides = {new Ride(2.0, 5,RideType.NORMAL_RIDE),
                new Ride(0.1,1,RideType.NORMAL_RIDE)};
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
        double distance =2.0;
        int time=5;
        double fare= invoiceService.addRides(distance,time,RideType.PREMIUM_RIDE);
        Assert.assertEquals(40,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTimeForPremiumRide_shouldReturnMinimunFare() {
        double distance=.1;
        int time=1;
        double fare= invoiceService.addRides(distance,time,RideType.NORMAL_RIDE);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRidesForPremiumRide_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5,RideType.PREMIUM_RIDE),
                new Ride(0.1,1,RideType.PREMIUM_RIDE)};
        //when(subscription.addRides(any(Double.class),any(Integer.class))).thenReturn(60.0);
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRideForPremiumRide_shouldReturnInvoiceSummary() {
        try{
            String userId="milan";
            Ride[] rides = {new Ride(2.0, 5,RideType.NORMAL_RIDE),
                    new Ride(3,10,RideType.PREMIUM_RIDE)};
            invoiceService.addRides(userId,rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 90.0);
            Assert.assertEquals(expectedInvoiceSummary,summary);
        }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
        }
    }

}
