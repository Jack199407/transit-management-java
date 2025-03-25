package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class ElectricLightRailConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal ELECTRIC_LIGHT_RAIL_CONSUMPTION_RATE = new BigDecimal("0.2");
    @Override
    public BigDecimal calculateConsumption(BigDecimal miles) {
        return miles.multiply(ELECTRIC_LIGHT_RAIL_CONSUMPTION_RATE);
    }
}
