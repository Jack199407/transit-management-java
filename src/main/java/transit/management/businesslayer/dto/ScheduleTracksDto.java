package transit.management.businesslayer.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleTracksDto {
    private Integer scheduleId;
    private Integer vehicleId;
    private Integer routeId;
    private String routeName;
    private Integer stationId;
    private String stationName;
    private Integer stationOrder;
    private Date departTime;
    private Date arrivalTime;
    private BigDecimal realMiles;
    private BigDecimal realConsumption;

    private boolean done;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(Integer stationOrder) {
        this.stationOrder = stationOrder;
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
