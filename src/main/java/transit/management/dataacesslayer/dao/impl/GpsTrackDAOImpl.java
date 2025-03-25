package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.GpsTrackDAO;
import transit.management.transferobjects.GpsTrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GpsTrackDAOImpl implements GpsTrackDAO {
    private final DataSourceManager dataSource;

    public GpsTrackDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(GpsTrack entity) {
        return 0;
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
}
