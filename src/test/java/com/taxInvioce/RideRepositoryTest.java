package com.taxInvioce;

import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void givenUser_whenFound_shouldReturnRideList() {
        try{
            RideRepository rideRepository = new RideRepository();
            Ride[] rides={new Ride(2.0,5,RideType.NORMAL_RIDE),
                   new Ride(.1,1,RideType.NORMAL_RIDE)};
            rideRepository.addRides("milan",rides);
            Assert.assertEquals(rideRepository.getRides("milan").length,rides.length);
        }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
        }
    }

    @Test
    public void givenUserWith_whenMultipleRidesAdded_shouldReturnCount() {
        try{
            RideRepository rideRepository = new RideRepository();
            Ride[] rides={new Ride(2.0,5,RideType.NORMAL_RIDE),
                new Ride(.1,1,RideType.NORMAL_RIDE)};
            rideRepository.addRides("milan",rides);
            Ride[] rides1={new Ride(3.0,10,RideType.NORMAL_RIDE),
                new Ride(.2,1,RideType.NORMAL_RIDE)};
            rideRepository.addRides("milan",rides1);
            Assert.assertEquals(rideRepository.getRides("milan").length,2);
        }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
        }
    }

    @Test
    public void givenUserNameAsNull_whenAdded_shouldThrowException() {
       try{
           RideRepository rideRepository = new RideRepository();
           Ride[] rides={new Ride(2.0,5,RideType.NORMAL_RIDE),
                new Ride(.1,1,RideType.NORMAL_RIDE)};
           rideRepository.addRides(null,rides);
       }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
       }
    }

    @Test
    public void givenUserRidesAsNull_whenAdded_shouldThrowException() {
        try{
            RideRepository rideRepository = new RideRepository();
            Ride[] rides=null;
            rideRepository.addRides("Milan",rides);
        }catch(InvoiceException e){
            e.printStackTrace();
            Assert.assertEquals(InvoiceException.ExceptionType.NULL_VALUE_ENTERED,e.type);
        }
    }
}
