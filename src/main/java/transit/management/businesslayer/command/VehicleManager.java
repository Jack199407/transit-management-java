package transit.management.businesslayer.command;

import transit.management.dataacesslayer.dao.VehicleDAO;
import transit.management.dataacesslayer.dao.impl.VehicleDAOImpl;

import java.sql.SQLException;

public class VehicleManager {

    private final VehicleDAO vehicleDAO = new VehicleDAOImpl();

    public void assignRoute(Integer vehicleId, Integer routeId) throws SQLException {
        // vehicle id更新数据库中数据的routeId
        boolean update = vehicleDAO.updateRouteIdByVehicleId(vehicleId, routeId);
    }
}
