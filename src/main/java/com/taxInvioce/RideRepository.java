package com.taxInvioce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides=null;


    public RideRepository() {
        this.userRides = new HashMap<String, ArrayList<Ride>>();
    }

    public void addRides(String userId, Ride[] rides) throws InvoiceException {

        if(userId==null||rides==null) {
            throw new InvoiceException("Null Value Entered"
                    ,InvoiceException.ExceptionType.NULL_VALUE_ENTERED);
        }
        ArrayList<Ride> rideList=this.userRides.get(userId);
        if(rideList==null){
            this.userRides.put(userId,new ArrayList<>(Arrays.asList(rides)));
        }
    }

    public Ride[] getRides(String userId) {
    return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
