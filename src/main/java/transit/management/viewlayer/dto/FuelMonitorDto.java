package transit.management.viewlayer.dto;

import java.math.BigDecimal;

public class FuelMonitorDto {
    private Integer vehicleId;
    private String fuelType;
    private BigDecimal realTotalMiles;
    private BigDecimal realTotalConsumption;
    private BigDecimal fuelConsumptionRate;
    private BigDecimal minConsumption;
    private BigDecimal maxConsumption;
    private boolean normal;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getRealTotalMiles() {
        return realTotalMiles;
    }

    public void setRealTotalMiles(BigDecimal realTotalMiles) {
        this.realTotalMiles = realTotalMiles;
    }

    public BigDecimal getRealTotalConsumption() {
        return realTotalConsumption;
    }

    public void setRealTotalConsumption(BigDecimal realTotalConsumption) {
        this.realTotalConsumption = realTotalConsumption;
    }

    public BigDecimal getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(BigDecimal fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }

    public BigDecimal getMinConsumption() {
        return minConsumption;
    }

    public void setMinConsumption(BigDecimal minConsumption) {
        this.minConsumption = minConsumption;
    }

    public BigDecimal getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(BigDecimal maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }
}
