package com.taxInvioce;

public class InvoiceService {

    private final RideRepository rideRepository;
    ISubscription subscription;

    public InvoiceService() {
        this.rideRepository=new RideRepository();
    }

    public double addRides(double distance, int time,RideType type) {
        subscription=SubscriptionFactory.createObject(type);
        return subscription.addRides(distance, time);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for(Ride ride: rides){
            totalFare+=this.addRides(ride.distance, ride.time,ride.type);
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
