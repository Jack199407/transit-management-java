package transit.management.businesslayer.strategy.impl;

import transit.management.businesslayer.strategy.ConsumptionStrategy;

import java.math.BigDecimal;

public class DieselElectricTrainConsumptionStrategy implements ConsumptionStrategy {
    public static final BigDecimal DIESEL_ELECTRIC_TRAIN_CONSUMPTION_RATE = new BigDecimal("0.7");
    @Override
    public BigDecimal calculateConsumption(BigDecimal miles) {
        return miles.multiply(DIESEL_ELECTRIC_TRAIN_CONSUMPTION_RATE);
    }
}
