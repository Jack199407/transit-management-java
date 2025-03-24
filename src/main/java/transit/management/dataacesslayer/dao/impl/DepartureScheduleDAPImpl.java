package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.DepartureScheduleDAO;
import transit.management.transferobjects.DepartureSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartureScheduleDAPImpl implements DepartureScheduleDAO {

    private final DataSourceManager dataSource;

    public DepartureScheduleDAPImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(DepartureSchedule schedule) throws SQLException {
        String sql = "INSERT INTO departure_schedule (" +
                "vehicle_id, route_id, expected_depart_time, expected_arrival_time, status" +
                ") VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getVehicleId());
            stmt.setInt(2, schedule.getRouteId());

            stmt.setTimestamp(3, new java.sql.Timestamp(schedule.getExpectedDepartTime().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(schedule.getExpectedArrivalTime().getTime()));

            stmt.setString(5, schedule.getStatus()); // ENUM 字段

            return stmt.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(DepartureSchedule entity) {
        return 0;
    }

    @Override
    public DepartureSchedule selectById(int id) {
        return null;
    }

    @Override
    public List<DepartureSchedule> selectAll() {
        return null;
    }

    @Override
    public List<DepartureSchedule> listAllByVehicleId(Integer vehicleId) throws SQLException {
        List<DepartureSchedule> schedules = new ArrayList<>();

        String sql = "SELECT * FROM departure_schedule WHERE vehicle_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DepartureSchedule schedule = new DepartureSchedule.Builder()
                            .id(rs.getInt("id"))
                            .vehicleId(rs.getInt("vehicle_id"))
                            .routeId(rs.getInt("route_id"))
                            .expectedDepartTime(rs.getTimestamp("expected_depart_time"))
                            .expectedArrivalTime(rs.getTimestamp("expected_arrival_time"))
                            .status(rs.getString("status"))
                            .build();

                    schedules.add(schedule);
                }
            }
        }

        return schedules;
    }

    @Override
    public boolean updateStatus(Integer scheduleId, String newStatus) throws SQLException {
        String sql = "UPDATE departure_schedule SET status = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, scheduleId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}
