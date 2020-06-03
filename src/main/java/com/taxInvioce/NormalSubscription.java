package com.taxInvioce;

public class NormalSubscription implements ISubscription{

    private static final double MINIMUM_COST_PER_KILOMETER =10;
    private static final int COST_PER_TIME =1;
    private static final int MINIMUN_FARE =5;

    @Override
    public double addRides(double distance, int time) {
        double totalFare= distance*MINIMUM_COST_PER_KILOMETER+time*COST_PER_TIME;
        if(totalFare<MINIMUN_FARE)
            return MINIMUN_FARE;
        return totalFare;
        }
}

