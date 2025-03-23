package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class BusConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal BUS_CONSUMPTION_RATE = new BigDecimal("0.5");
    @Override
    public double calculateConsumption(double miles) {
        return miles * BUS_CONSUMPTION_RATE.doubleValue();
    }
}
