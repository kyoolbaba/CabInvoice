package com.taxInvioce;

public enum RideType {
    NORMAL_RIDE(10,1,5),PREMIUM_RIDE(15,2,20);
    private int[] fare;
    RideType(int ... values) {
        this.fare = values;
    }

    public int minimumCostPerKm(){return fare[0];}

    public int costPerTime(){return fare[1];}

    public int minimunFare(){return fare[2];}

}
