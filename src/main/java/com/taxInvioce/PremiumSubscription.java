package com.taxInvioce;

public class PremiumSubscription implements ISubscription {

    private static final double MINIMUM_COST_PER_KILOMETER =15;
    private static final int COST_PER_TIME =2;
    private static final int MINIMUN_FARE =20;

    @Override
    public double addRides(double distance, int time) {
        double totalFare= distance*MINIMUM_COST_PER_KILOMETER+time*COST_PER_TIME;
        if(totalFare<MINIMUN_FARE)
            return MINIMUN_FARE;
        return totalFare;
    }
}
