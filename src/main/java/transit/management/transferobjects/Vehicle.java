package transit.management.transferobjects;

import transit.management.businesslayer.observer.Observer;
import transit.management.businesslayer.observer.Subject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Subject {
    private Integer id;
    private String vehicleType;
    private String fuelType;
    private BigDecimal fuelConsumptionRate;
    private Integer maxPassengers;
    private Integer currentAssignedRouteId;
    private BigDecimal realTotalMiles;
    private BigDecimal realTotalConsumption;
    private BigDecimal maintainGapMiles;
    private BigDecimal milesFromLastMaintenance;
    private boolean needMaintenance;
    private String currentRouteName;


    public String getCurrentRouteName() {
        return currentRouteName;
    }

    public void setCurrentRouteName(String currentRouteName) {
        this.currentRouteName = currentRouteName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelConsumptionRate(BigDecimal fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }

    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public void setCurrentAssignedRouteId(Integer currentAssignedRouteId) {
        this.currentAssignedRouteId = currentAssignedRouteId;
    }

    public void setRealTotalMiles(BigDecimal realTotalMiles) {
        this.realTotalMiles = realTotalMiles;
    }

    public void setRealTotalConsumption(BigDecimal realTotalConsumption) {
        this.realTotalConsumption = realTotalConsumption;
    }

    public void setMaintainGapMiles(BigDecimal maintainGapMiles) {
        this.maintainGapMiles = maintainGapMiles;
    }

    public void setMilesFromLastMaintenance(BigDecimal milesFromLastMaintenance) {
        this.milesFromLastMaintenance = milesFromLastMaintenance;
        notifyObservers();
    }

    public boolean isNeedMaintenance() {
        return needMaintenance;
    }

    public void setNeedMaintenance(boolean needMaintenance) {
        this.needMaintenance = needMaintenance;
    }

    private List<Observer> observers = new ArrayList<>();

    public BigDecimal getMilesFromLastMaintenance() {
        return milesFromLastMaintenance;
    }

    private Vehicle(Builder builder) {
        this.id = builder.id;
        this.vehicleType = builder.vehicleType;
        this.fuelType = builder.fuelType;
        this.fuelConsumptionRate = builder.fuelConsumptionRate;
        this.maxPassengers = builder.maxPassengers;
        this.currentAssignedRouteId = builder.currentAssignedRouteId;
        this.realTotalMiles = builder.realTotalMiles;
        this.realTotalConsumption = builder.realTotalConsumption;
        this.maintainGapMiles = builder.maintainGapMiles;
        this.milesFromLastMaintenance = builder.milesFromLastMaintenance;
        this.needMaintenance = builder.needMaintenance;
    }

    public Integer getId() {
        return id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public BigDecimal getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    public Integer getCurrentAssignedRouteId() {
        return currentAssignedRouteId;
    }

    public BigDecimal getRealTotalMiles() {
        return realTotalMiles;
    }

    public BigDecimal getRealTotalConsumption() {
        return realTotalConsumption;
    }

    public BigDecimal getMaintainGapMiles() {
        return maintainGapMiles;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public static class Builder {
        private Integer id;
        private String vehicleType;
        private String fuelType;
        private BigDecimal fuelConsumptionRate;
        private Integer maxPassengers;
        private Integer currentAssignedRouteId;
        private BigDecimal realTotalMiles;
        private BigDecimal realTotalConsumption;
        private BigDecimal maintainGapMiles;
        private BigDecimal milesFromLastMaintenance;
        private boolean needMaintenance;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder vehicleType(String vehicleType) {
            this.vehicleType = vehicleType == null ? null : vehicleType.trim();
            return this;
        }

        public Builder fuelType(String fuelType) {
            this.fuelType = fuelType == null ? null : fuelType.trim();
            return this;
        }

        public Builder fuelConsumptionRate(BigDecimal fuelConsumptionRate) {
            this.fuelConsumptionRate = fuelConsumptionRate;
            return this;
        }

        public Builder maxPassengers(Integer maxPassengers) {
            this.maxPassengers = maxPassengers;
            return this;
        }

        public Builder currentAssignedRouteId(Integer currentAssignedRouteId) {
            this.currentAssignedRouteId = currentAssignedRouteId;
            return this;
        }

        public Builder realTotalMiles(BigDecimal realTotalMiles) {
            this.realTotalMiles = realTotalMiles;
            return this;
        }

        public Builder realTotalConsumption(BigDecimal realTotalConsumption) {
            this.realTotalConsumption = realTotalConsumption;
            return this;
        }

        public Builder maintainGapMiles(BigDecimal maintainGapMiles) {
            this.maintainGapMiles = maintainGapMiles;
            return this;
        }

        public Builder milesFromLastMaintenance(BigDecimal milesFromLastMaintenance) {
            this.milesFromLastMaintenance = milesFromLastMaintenance;
            return this;
        }

        public Builder needMaintenance(boolean needMaintenance) {
            this.needMaintenance = needMaintenance;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
