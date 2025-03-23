package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class ElectricLightRailConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal ELECTRIC_LIGHT_RAIL_CONSUMPTION_RATE = new BigDecimal("0.2");
    @Override
    public double calculateConsumption(double miles) {
        return miles * ELECTRIC_LIGHT_RAIL_CONSUMPTION_RATE.doubleValue();
    }
}
