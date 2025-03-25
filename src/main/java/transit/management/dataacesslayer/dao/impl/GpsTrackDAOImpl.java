package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.GpsTrackDAO;
import transit.management.transferobjects.GpsTrack;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GpsTrackDAOImpl implements GpsTrackDAO {
    private final DataSourceManager dataSource;

    public GpsTrackDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(GpsTrack track) throws SQLException {
        String sql = "INSERT INTO gps_track (" +
                "departure_schedule_id, station_id, arrival_time, depart_time, real_miles, real_consumption" +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, track.getDepartureScheduleId());
            stmt.setInt(2, track.getStationId());
            stmt.setTimestamp(3, new Timestamp(track.getArrivalTime().getTime()));
            stmt.setTimestamp(4, new Timestamp(track.getDepartTime().getTime()));
            stmt.setBigDecimal(5, track.getRealMiles());
            stmt.setBigDecimal(6, track.getRealConsumption());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(GpsTrack entity) {
        return 0;
    }

    @Override
    public GpsTrack selectById(int id) {
        return null;
    }

    @Override
    public List<GpsTrack> selectAll() {
        return null;
    }

    @Override
    public List<GpsTrack> listByScheduleId(Integer departureScheduleId) throws SQLException {
        List<GpsTrack> tracks = new ArrayList<>();
        String sql = "SELECT * FROM gps_track WHERE departure_schedule_id = ? ORDER BY station_id ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departureScheduleId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    GpsTrack track = new GpsTrack.Builder()
                            .id(rs.getInt("id"))
                            .departureScheduleId(rs.getInt("departure_schedule_id"))
                            .stationId(rs.getInt("station_id"))
                            .arrivalTime(rs.getTimestamp("arrival_time"))
                            .departTime(rs.getTimestamp("depart_time"))
                            .realMiles(rs.getBigDecimal("real_miles"))
                            .realConsumption(rs.getBigDecimal("real_consumption"))
                            .build();
                    tracks.add(track);
                }
            }
        }

        return tracks;
    }

    @Override
    public GpsTrack queryByScheduleIdAndStationId(Integer scheduleId, Integer stationId) throws SQLException {
        String sql = "SELECT * FROM gps_track WHERE departure_schedule_id = ? AND station_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);
            stmt.setInt(2, stationId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GpsTrack.Builder()
                            .id(rs.getInt("id"))
                            .departureScheduleId(rs.getInt("departure_schedule_id"))
                            .stationId(rs.getInt("station_id"))
                            .arrivalTime(rs.getTimestamp("arrival_time"))
                            .departTime(rs.getTimestamp("depart_time"))
                            .realMiles(rs.getBigDecimal("real_miles"))
                            .realConsumption(rs.getBigDecimal("real_consumption"))
                            .build();
                }
            }
        }

        return null;
    }

    @Override
    public boolean updateByScheduleIdAndStationId(Integer scheduleId, Integer stationId,
                                                  Date departTime, Date arrivalTime,
                                                  BigDecimal realMiles, BigDecimal realConsumption) throws SQLException {

        String sql = "UPDATE gps_track SET " +
                "arrival_time = ?, " +
                "depart_time = ?, " +
                "real_miles = ?, " +
                "real_consumption = ? " +
                "WHERE departure_schedule_id = ? AND station_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(arrivalTime.getTime()));
            stmt.setTimestamp(2, new Timestamp(departTime.getTime()));
            stmt.setBigDecimal(3, realMiles);
            stmt.setBigDecimal(4, realConsumption);
            stmt.setInt(5, scheduleId);
            stmt.setInt(6, stationId);

            return stmt.executeUpdate() > 0;
        }
    }
}
