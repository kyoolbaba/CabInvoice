package com.taxInvioce;

public class InvoiceService {

    private final RideRepository rideRepository;

    public InvoiceService(  ) {
        this.rideRepository=new RideRepository();
    }



    public double addRides(double distance, int time,RideType type) {
        double totalFare=(type.minimumCostPerKm()*distance)+(type.costPerTime()*time);
        if(totalFare<type.minimunFare())
            return type.minimunFare();
        return totalFare;
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
