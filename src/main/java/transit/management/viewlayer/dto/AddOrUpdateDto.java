package transit.management.viewlayer.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AddOrUpdateDto {
    private Integer scheduleId;
    private Integer stationId;
    private Date departTime;
    private Date arrivalTime;
    private BigDecimal realMiles;
    private BigDecimal realConsumption;
    private Integer vehicleId;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BigDecimal getRealMiles() {
        return realMiles;
    }

    public void setRealMiles(BigDecimal realMiles) {
        this.realMiles = realMiles;
    }

    public BigDecimal getRealConsumption() {
        return realConsumption;
    }

    public void setRealConsumption(BigDecimal realConsumption) {
        this.realConsumption = realConsumption;
    }
}
