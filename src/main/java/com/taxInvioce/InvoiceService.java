package com.taxInvioce;

public class InvoiceService {

    private static final double MINIMUM_COST_PER_KILOMETER =10;
    private static final int COST_PER_TIME =1;
    private static final int MINIMUN_FARE =5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER =10;
    private static final int PREMIUM_COST_PER_TIME =1;
    private static final int PREMIUM_MINIMUN_FARE =5;
    private final RideRepository rideRepository;

    public double addRides(double distance, int time) {
    double totalFare= distance*MINIMUM_COST_PER_KILOMETER+time*COST_PER_TIME;
    if(totalFare<MINIMUN_FARE)
        return MINIMUN_FARE;
    return totalFare;
    }

    public InvoiceService() {
        this.rideRepository=new RideRepository();
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for(Ride ride: rides){
            totalFare+=this.addRides(ride.distance, ride.time);
    }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
