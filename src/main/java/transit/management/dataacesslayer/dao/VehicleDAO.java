package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.Vehicle;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends BaseDAO<Vehicle>{
    boolean updateRouteIdByVehicleId(Integer vehicleId, Integer routeId) throws SQLException;

    int updateMilesAndConsumptionByVehicleId
            (Integer vehicleId, BigDecimal diffMiles, BigDecimal diffConsumption) throws SQLException;

    int updateMilesFromLastMaintenanceById(Integer vehicleId) throws SQLException;
}
