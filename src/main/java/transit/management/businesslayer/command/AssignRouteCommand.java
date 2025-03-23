package transit.management.businesslayer.command;

import java.sql.SQLException;

public class AssignRouteCommand implements Command{
    private VehicleManager manager;
    private Integer vehicleId;
    private Integer routeId;

    public AssignRouteCommand(VehicleManager manager, Integer vehicleId, Integer routeId) {
        this.manager = manager;
        this.vehicleId = vehicleId;
        this.routeId = routeId;
    }
    @Override
    public void execute() throws SQLException {
        manager.assignRoute(vehicleId, routeId);
    }
}
