package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class BusConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal BUS_CONSUMPTION_RATE = new BigDecimal("0.5");
    @Override
    public BigDecimal calculateConsumption(BigDecimal miles) {
        return miles.multiply(BUS_CONSUMPTION_RATE);
    }
}
