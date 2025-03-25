package transit.management.businesslayer.strategy;

import java.math.BigDecimal;

public interface ConsumptionStrategy {
    BigDecimal calculateConsumption(BigDecimal miles);
}
