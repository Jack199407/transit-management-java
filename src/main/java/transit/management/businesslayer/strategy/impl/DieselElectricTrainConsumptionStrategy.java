package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class DieselElectricTrainConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal DIESEL_ELECTRIC_TRAIN_CONSUMPTION_RATE = new BigDecimal("0.7");
    @Override
    public double calculateConsumption(double miles) {
        return miles * DIESEL_ELECTRIC_TRAIN_CONSUMPTION_RATE.doubleValue();
    }
}
