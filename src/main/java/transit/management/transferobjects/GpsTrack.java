package transit.management.transferobjects;

import java.math.BigDecimal;
import java.util.Date;

public class GpsTrack {
    private Integer id;
    private Integer departureScheduleId;
    private Integer stationId;
    private Date arrivalTime;
    private Date departTime;
    private BigDecimal realMiles;
    private BigDecimal realConsumption;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartureScheduleId(Integer departureScheduleId) {
        this.departureScheduleId = departureScheduleId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public void setRealMiles(BigDecimal realMiles) {
        this.realMiles = realMiles;
    }

    public void setRealConsumption(BigDecimal realConsumption) {
        this.realConsumption = realConsumption;
    }

    private GpsTrack(Builder builder) {
        this.id = builder.id;
        this.departureScheduleId = builder.departureScheduleId;
        this.stationId = builder.stationId;
        this.arrivalTime = builder.arrivalTime;
        this.departTime = builder.departTime;
        this.realMiles = builder.realMiles;
        this.realConsumption = builder.realConsumption;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDepartureScheduleId() {
        return departureScheduleId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public BigDecimal getRealMiles() {
        return realMiles;
    }

    public BigDecimal getRealConsumption() {
        return realConsumption;
    }

    public static class Builder {
        private Integer id;
        private Integer departureScheduleId;
        private Integer stationId;
        private Date arrivalTime;
        private Date departTime;
        private BigDecimal realMiles;
        private BigDecimal realConsumption;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder departureScheduleId(Integer departureScheduleId) {
            this.departureScheduleId = departureScheduleId;
            return this;
        }

        public Builder stationId(Integer stationId) {
            this.stationId = stationId;
            return this;
        }

        public Builder arrivalTime(Date arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder departTime(Date departTime) {
            this.departTime = departTime;
            return this;
        }

        public Builder realMiles(BigDecimal realMiles) {
            this.realMiles = realMiles;
            return this;
        }

        public Builder realConsumption(BigDecimal realConsumption) {
            this.realConsumption = realConsumption;
            return this;
        }

        public GpsTrack build() {
            return new GpsTrack(this);
        }
    }
}
