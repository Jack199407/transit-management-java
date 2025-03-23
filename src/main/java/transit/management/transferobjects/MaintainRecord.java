package transit.management.transferobjects;

import java.util.Date;

public class MaintainRecord {
    private Integer id;
    private Integer vehicleId;
    private Date maintainTime;

    private MaintainRecord(Builder builder) {
        this.id = builder.id;
        this.vehicleId = builder.vehicleId;
        this.maintainTime = builder.maintainTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public static class Builder {
        private Integer id;
        private Integer vehicleId;
        private Date maintainTime;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder vehicleId(Integer vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder maintainTime(Date maintainTime) {
            this.maintainTime = maintainTime;
            return this;
        }

        public MaintainRecord build() {
            return new MaintainRecord(this);
        }
    }
}
