package transit.management.viewlayer.dto;

import transit.management.transferobjects.MaintainRecord;

import java.util.List;

public class MaintenanceInfoDto {
    private Integer vehicleId;
    private boolean needMaintain;
    private List<MaintainRecord> records;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isNeedMaintain() {
        return needMaintain;
    }

    public void setNeedMaintain(boolean needMaintain) {
        this.needMaintain = needMaintain;
    }

    public List<MaintainRecord> getRecords() {
        return records;
    }

    public void setRecords(List<MaintainRecord> records) {
        this.records = records;
    }
}
