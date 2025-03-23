package transit.management.businesslayer.factory;

import transit.management.businesslayer.strategy.impl.BusConsumptionStrategy;
import transit.management.businesslayer.strategy.impl.DieselElectricTrainConsumptionStrategy;
import transit.management.businesslayer.strategy.impl.ElectricLightRailConsumptionStrategy;
import transit.management.transferobjects.Vehicle;

import java.math.BigDecimal;

public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type) {
            case "Bus":
                return new Vehicle.Builder()
                        .vehicleType("Bus")
                        .fuelType("Diesel")
                        .fuelConsumptionRate(BusConsumptionStrategy.BUS_CONSUMPTION_RATE)
                        .maxPassengers(50)
                        .maintainGapMiles(new BigDecimal(10000))
                        .realTotalMiles(new BigDecimal(0))
                        .realTotalConsumption(new BigDecimal(0))
                        .milesFromLastMaintenance(new BigDecimal(0))
                        .needMaintenance(false)
                        .currentAssignedRouteId(null)
                        .build();
            case "Electric Light Rail":
                return new Vehicle.Builder()
                        .vehicleType("Electric Light Rail")
                        .fuelType("Electric")
                        .fuelConsumptionRate(ElectricLightRailConsumptionStrategy.ELECTRIC_LIGHT_RAIL_CONSUMPTION_RATE)
                        .maxPassengers(150)
                        .maintainGapMiles(new BigDecimal(20000))
                        .realTotalMiles(new BigDecimal(0))
                        .realTotalConsumption(new BigDecimal(0))
                        .milesFromLastMaintenance(new BigDecimal(0))
                        .needMaintenance(false)
                        .currentAssignedRouteId(null)
                        .build();
            case "Diesel Electric Train":
                return new Vehicle.Builder()
                        .vehicleType("Diesel Electric Train")
                        .fuelType("Hybrid")
                        .fuelConsumptionRate(DieselElectricTrainConsumptionStrategy.DIESEL_ELECTRIC_TRAIN_CONSUMPTION_RATE)
                        .maxPassengers(300)
                        .maintainGapMiles(new BigDecimal(50000))
                        .realTotalMiles(new BigDecimal(0))
                        .realTotalConsumption(new BigDecimal(0))
                        .milesFromLastMaintenance(new BigDecimal(0))
                        .needMaintenance(false)
                        .currentAssignedRouteId(null)
                        .build();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}

