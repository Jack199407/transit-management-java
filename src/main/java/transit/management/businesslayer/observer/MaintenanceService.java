package transit.management.businesslayer.observer;

import transit.management.transferobjects.Vehicle;

public class MaintenanceService implements Observer {
    @Override
    public void update(Vehicle vehicle) {
        if (vehicle.getMilesFromLastMaintenance().compareTo(vehicle.getMaintainGapMiles()) > 0) {
            vehicle.setNeedMaintenance(true);
        }
    }
}
