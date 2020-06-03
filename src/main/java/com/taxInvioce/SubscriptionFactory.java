package com.taxInvioce;

public class SubscriptionFactory {
    public static ISubscription createObject(RideType type){
            if(type.equals(RideType.NORMAL_RIDE))
                return new NormalSubscription();
            return new PremiumSubscription();
    }
}
