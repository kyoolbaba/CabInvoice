package com.taxInvioce;

public class InvoiceService {

    private final RideRepository rideRepository;
    ISubscription subscription;

    public InvoiceService(RideType type) {
        this.rideRepository=new RideRepository();
        subscription=SubscriptionFactory.createObject(type);
    }

    public double addRides(double distance, int time) {
    return subscription.addRides(distance, time);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for(Ride ride: rides){
            totalFare+=this.addRides(ride.distance, ride.time);
    }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) throws InvoiceException {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
