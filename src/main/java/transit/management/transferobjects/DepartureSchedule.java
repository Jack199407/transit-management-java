package transit.management.transferobjects;

import java.util.Date;

public class DepartureSchedule {
    private Integer id;
    private Integer vehicleId;
    private Integer routeId;
    private Date expectedDepartTime;
    private Date expectedArrivalTime;
    private String status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public void setExpectedDepartTime(Date expectedDepartTime) {
        this.expectedDepartTime = expectedDepartTime;
    }

    public void setExpectedArrivalTime(Date expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private DepartureSchedule(Builder builder) {
        this.id = builder.id;
        this.vehicleId = builder.vehicleId;
        this.routeId = builder.routeId;
        this.expectedDepartTime = builder.expectedDepartTime;
        this.expectedArrivalTime = builder.expectedArrivalTime;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public Date getExpectedDepartTime() {
        return expectedDepartTime;
    }

    public Date getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private Integer id;
        private Integer vehicleId;
        private Integer routeId;
        private Date expectedDepartTime;
        private Date expectedArrivalTime;
        private String status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder vehicleId(Integer vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder routeId(Integer routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder expectedDepartTime(Date expectedDepartTime) {
            this.expectedDepartTime = expectedDepartTime;
            return this;
        }

        public Builder expectedArrivalTime(Date expectedArrivalTime) {
            this.expectedArrivalTime = expectedArrivalTime;
            return this;
        }

        public Builder status(String status) {
            this.status = status == null ? null : status.trim();
            return this;
        }

        public DepartureSchedule build() {
            return new DepartureSchedule(this);
        }
    }
}
