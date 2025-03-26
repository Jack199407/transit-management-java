package transit.management.dataacesslayer.dao.impl;

import transit.management.businesslayer.observer.MaintenanceService;
import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.VehicleDAO;
import transit.management.transferobjects.Vehicle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    private final DataSourceManager dataSource;

    public VehicleDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }

    @Override
    public int insert(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicle (" +
                "vehicle_type, fuel_type, fuel_consumption_rate, max_passengers, " +
                "current_assigned_route_id, real_total_miles, real_total_consumption, " +
                "maintain_gap_miles, miles_from_last_maintenance " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getFuelType());
            stmt.setBigDecimal(3, vehicle.getFuelConsumptionRate());
            stmt.setInt(4, vehicle.getMaxPassengers());

            // 允许 currentAssignedRouteId 为 null
            if (vehicle.getCurrentAssignedRouteId() != null) {
                stmt.setInt(5, vehicle.getCurrentAssignedRouteId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setBigDecimal(6, vehicle.getRealTotalMiles());
            stmt.setBigDecimal(7, vehicle.getRealTotalConsumption());
            stmt.setBigDecimal(8, vehicle.getMaintainGapMiles());
            stmt.setBigDecimal(9, vehicle.getMilesFromLastMaintenance());

            return stmt.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(Vehicle entity) {
        return 0;
    }

    @Override
    public Vehicle selectById(int id) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle.Builder()
                            .id(rs.getInt("id"))
                            .vehicleType(rs.getString("vehicle_type"))
                            .fuelType(rs.getString("fuel_type"))
                            .fuelConsumptionRate(rs.getBigDecimal("fuel_consumption_rate"))
                            .maxPassengers(rs.getInt("max_passengers"))
                            .currentAssignedRouteId((Integer) rs.getObject("current_assigned_route_id"))
                            .realTotalMiles(rs.getBigDecimal("real_total_miles"))
                            .realTotalConsumption(rs.getBigDecimal("real_total_consumption"))
                            .maintainGapMiles(rs.getBigDecimal("maintain_gap_miles"))
                            .milesFromLastMaintenance(rs.getBigDecimal("miles_from_last_maintenance"))
                            .build();
                    MaintenanceService service = new MaintenanceService();
                    vehicle.addObserver(service);
                    vehicle.setMilesFromLastMaintenance(rs.getBigDecimal("miles_from_last_maintenance"));
                    return vehicle;
                }
            }
        }

        return null;
    }

    @Override
    public List<Vehicle> selectAll() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle.Builder()
                        .id(rs.getInt("id"))
                        .vehicleType(rs.getString("vehicle_type"))
                        .fuelType(rs.getString("fuel_type"))
                        .fuelConsumptionRate(rs.getBigDecimal("fuel_consumption_rate"))
                        .maxPassengers(rs.getInt("max_passengers"))
                        .currentAssignedRouteId((Integer) rs.getObject("current_assigned_route_id"))
                        .realTotalMiles(rs.getBigDecimal("real_total_miles"))
                        .realTotalConsumption(rs.getBigDecimal("real_total_consumption"))
                        .maintainGapMiles(rs.getBigDecimal("maintain_gap_miles"))
                        .build();
                MaintenanceService service = new MaintenanceService();
                vehicle.addObserver(service);
                vehicle.setMilesFromLastMaintenance(rs.getBigDecimal("miles_from_last_maintenance"));
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    @Override
    public boolean updateRouteIdByVehicleId(Integer vehicleId, Integer routeId) throws SQLException {
        String sql = "UPDATE vehicle SET current_assigned_route_id = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeId);
            stmt.setInt(2, vehicleId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public int updateMilesAndConsumptionByVehicleId
            (Integer vehicleId, BigDecimal diffMiles, BigDecimal diffConsumption) throws SQLException {
        String sql = "UPDATE vehicle SET " +
                "real_total_miles = real_total_miles + ?, " +
                "real_total_consumption = real_total_consumption + ?, " +
                "miles_from_last_maintenance = miles_from_last_maintenance + ? " +
                "WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, diffMiles);
            stmt.setBigDecimal(2, diffConsumption);
            stmt.setBigDecimal(3, diffMiles);
            stmt.setInt(4, vehicleId);

            return stmt.executeUpdate();
        }
    }

    @Override
    public int updateMilesFromLastMaintenanceById(Integer vehicleId) throws SQLException {
        String sql = "UPDATE vehicle SET " +
                "miles_from_last_maintenance = 0 " +
                "WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);

            return stmt.executeUpdate();
        }
    }
}
