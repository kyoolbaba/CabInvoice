package com.taxInvioce;

public class InvoiceSummary {
    private final double totalFare;
    private final int noOfRides;
    private final double averageFare;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceSummary)) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.totalFare, totalFare) == 0 &&
                noOfRides == that.noOfRides &&
                Double.compare(that.averageFare, averageFare) == 0;
    }


    public InvoiceSummary(int noOfRides, double totalFare) {
        this.noOfRides = noOfRides;
        this.totalFare =totalFare;
        this.averageFare =this.totalFare/this.noOfRides;
    }
}
